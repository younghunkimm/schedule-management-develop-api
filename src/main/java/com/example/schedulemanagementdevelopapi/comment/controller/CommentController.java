package com.example.schedulemanagementdevelopapi.comment.controller;

import com.example.schedulemanagementdevelopapi.comment.dto.request.CommentSaveRequestDto;
import com.example.schedulemanagementdevelopapi.comment.dto.request.CommentUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSaveResponseDto;
import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.comment.service.CommentService;
import com.example.schedulemanagementdevelopapi.global.dto.response.ErrorResponseDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment-Controller", description = "Comment CRUD API Endpoint")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    @Operation(
            summary = "댓글 생성",
            description = "로그인 세션을 사용해 댓글을 생성합니다.",
            parameters = {
                    @Parameter(name = "scheduleId", description = "일정 번호", example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (예: 제목/내용 누락)",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<CommentSaveResponseDto> save(
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long memberId,
            @PathVariable Long scheduleId,
            @Valid @RequestBody CommentSaveRequestDto requestDto
    ) {

        CommentSaveResponseDto commentSaveResponseDto =
                commentService.save(
                        memberId,
                        scheduleId,
                        requestDto.getContent()
                );

        return ResponseEntity.ok(commentSaveResponseDto);
    }

    @PatchMapping("/comments/{commentId}")
    @Operation(
            summary = "댓글 수정",
            description = "댓글을 수정합니다.",
            parameters = {
                    @Parameter(name = "commentId", description = "댓글 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<CommentUpdateResponseDto> update(
            @PathVariable Long commentId,
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long memberId,
            @Valid @RequestBody CommentUpdateRequestDto requestDto
    ) {

        CommentUpdateResponseDto commentUpdateResponseDto =
                commentService.update(
                        commentId,
                        memberId,
                        requestDto.getContent()
                );

        return ResponseEntity.ok(commentUpdateResponseDto);
    }

    @DeleteMapping("/comments/{commentId}")
    @Operation(
            summary = "댓글 삭제",
            description = "댓글을 삭제합니다.",
            parameters = {
                    @Parameter(name = "commentId", description = "댓글 번호", example = "1")
            }
    )
    @SecurityRequirement(name = "sessionCookie")
    public ResponseEntity<Void> delete(
            @PathVariable Long commentId,
            @Parameter(hidden = true) @SessionAttribute("LOGIN_MEMBER") Long memberId
    ) {

        commentService.delete(commentId, memberId);

        return ResponseEntity.ok().build();
    }
}
