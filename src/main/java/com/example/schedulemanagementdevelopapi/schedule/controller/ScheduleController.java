package com.example.schedulemanagementdevelopapi.schedule.controller;

import com.example.schedulemanagementdevelopapi.global.dto.response.ErrorResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchDetailResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchSummaryResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Schedule-Controller", description = "Schedule CRUD API Endpoint")
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "일정 생성", description = "로그인 세션을 사용해 일정을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (예: 제목/내용 누락)",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<ScheduleSaveResponseDto> save(
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long memberId,
            @Valid @RequestBody ScheduleSaveRequestDto requestDto
    ) {

        ScheduleSaveResponseDto scheduleSaveResponseDto =
                scheduleService.save(
                        memberId,
                        requestDto.getTitle(),
                        requestDto.getContent()
                );

        return ResponseEntity.ok(scheduleSaveResponseDto);
    }

    @GetMapping
    @Operation(
            summary = "일정 목록(댓글 수 포함) 조회",
            description = "일정 목록을 페이징하여 조회합니다.",
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호(0부터 시작)", example = "0"),
                    @Parameter(name = "size", description = "페이지 크기", example = "10"),
                    @Parameter(name = "sort", description = "수정일 기준으로 내림차순 정렬", hidden = true)
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<Page<ScheduleSearchSummaryResponseDto>> searchPageWithCommentCount(
            @ParameterObject @ModelAttribute ScheduleSearchConditionDto cond,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {

        return ResponseEntity.ok(scheduleService.searchPageWithCommentCount(cond, pageable));
    }

    @GetMapping("/{scheduleId}")
    @Operation(
            summary = "일정 단건(댓글 리스트 포함) 조회",
            description = "단건의 일정을 조회하고, 해당 일정의 댓글 리스트들을 함께 조회합니다.",
            parameters = {
                    @Parameter(name = "scheduleId", description = "일정 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<ScheduleSearchDetailResponseDto> findById(
            @PathVariable Long scheduleId
    ) {

        return ResponseEntity.ok(scheduleService.findById(scheduleId));
    }

    @PatchMapping("/{scheduleId}")
    @Operation(
            summary = "일정 수정",
            description = "일정을 수정합니다.",
            parameters = {
                    @Parameter(name = "scheduleId", description = "일정 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<ScheduleUpdateResponseDto> update(
            @PathVariable Long scheduleId,
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long memberId,
            @Valid @RequestBody ScheduleUpdateRequestDto requestDto
    ) {

        return ResponseEntity.ok(scheduleService.update(scheduleId, memberId, requestDto));
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(
            summary = "일정 삭제",
            description = "일정을 삭제합니다.",
            parameters = {
                    @Parameter(name = "scheduleId", description = "일정 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<Void> delete(
            @PathVariable Long scheduleId,
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long memberId
    ) {

        scheduleService.delete(scheduleId, memberId);

        return ResponseEntity.ok().build();
    }

}
