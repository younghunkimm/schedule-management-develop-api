package com.example.schedulemanagementdevelopapi.member.controller;

import com.example.schedulemanagementdevelopapi.member.dto.request.MemberLoginRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSaveRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberLoginResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
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
    public ResponseEntity<List<MemberSearchResponseDto>> search(
            @ModelAttribute MemberSearchConditionDto cond
    ) {

        return ResponseEntity.ok(memberService.search(cond));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberSearchResponseDto> findById(
            @PathVariable Long memberId
    ) {

        return ResponseEntity.ok(memberService.findById(memberId));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberUpdateResponseDto> update(
            @PathVariable Long memberId,
            @SessionAttribute("LOGIN_MEMBER") Long authMemberId,
            @Valid @RequestBody MemberUpdateRequestDto requestDto
    ) {

        return ResponseEntity.ok(memberService.update(memberId, authMemberId, requestDto));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long memberId,
            @SessionAttribute("LOGIN_MEMBER") Long authMemberId
    ) {

        memberService.delete(memberId, authMemberId);

        return ResponseEntity.ok().build();
    }

}
