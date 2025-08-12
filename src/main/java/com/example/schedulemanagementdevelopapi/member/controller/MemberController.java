package com.example.schedulemanagementdevelopapi.member.controller;

import com.example.schedulemanagementdevelopapi.global.dto.response.ErrorResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberLoginRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSaveRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberLoginResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Member-Controller", description = "Member CRUD + Auth API Endpoint")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일/비밀번호로 로그인하고 세션을 발급합니다.")
    public ResponseEntity<Void> login(
            @Valid @RequestBody MemberLoginRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {

        MemberLoginResponseDto memberLoginResponseDto = memberService.login(requestDto);

        HttpSession session = httpRequest.getSession();
        session.setAttribute("LOGIN_MEMBER", memberLoginResponseDto.id());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "서버의 세션과 브라우저의 쿠키를 제거하여 로그아웃합니다.")
    public ResponseEntity<Void> logout(
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {

        // 세션 제거
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 브라우저 쿠키 제거
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpResponse.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "이름, 이메일, 비밀번호를 입력받아 회원가입합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<MemberSaveResponseDto> signup(
            @Valid @RequestBody MemberSaveRequestDto requestDto
    ) {

        MemberSaveResponseDto memberSaveResponseDto =
                memberService.save(
                        requestDto.getName(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        return ResponseEntity.ok(memberSaveResponseDto);
    }

    @GetMapping
    @Operation(summary = "유저 전체 조회", description = "유저 목록을 조회합니다.")
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<List<MemberSearchResponseDto>> search(
            @ParameterObject @ModelAttribute MemberSearchConditionDto cond
    ) {

        return ResponseEntity.ok(memberService.search(cond));
    }

    @GetMapping("/{memberId}")
    @Operation(
            summary = "유저 단건 조회",
            description = "단건의 유저를 조회합니다.",
            parameters = {
                    @Parameter(name = "memberId", description = "회원 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<MemberSearchResponseDto> findById(
            @PathVariable Long memberId
    ) {

        return ResponseEntity.ok(memberService.findById(memberId));
    }

    @PatchMapping("/{memberId}")
    @Operation(
            summary = "유저 정보 수정",
            description = "유저의 정보를 수정합니다.",
            parameters = {
                    @Parameter(name = "memberId", description = "유저 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<MemberUpdateResponseDto> update(
            @PathVariable Long memberId,
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long authMemberId,
            @Valid @RequestBody MemberUpdateRequestDto requestDto
    ) {

        return ResponseEntity.ok(memberService.update(memberId, authMemberId, requestDto));
    }

    @DeleteMapping("/{memberId}")
    @Operation(
            summary = "유저 삭제",
            description = "유저을 삭제합니다.",
            parameters = {
                    @Parameter(name = "memberId", description = "유저 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<Void> delete(
            @PathVariable Long memberId,
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long authMemberId
    ) {

        memberService.delete(memberId, authMemberId);

        return ResponseEntity.ok().build();
    }

}
