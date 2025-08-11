package com.example.schedulemanagementdevelopapi.comment.controller;

import com.example.schedulemanagementdevelopapi.comment.dto.request.CommentSaveRequestDto;
import com.example.schedulemanagementdevelopapi.comment.dto.request.CommentUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSaveResponseDto;
import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentSaveResponseDto> save(
            @SessionAttribute("LOGIN_MEMBER") Long memberId,
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
    public ResponseEntity<CommentUpdateResponseDto> update(
            @PathVariable Long commentId,
            @SessionAttribute("LOGIN_MEMBER") Long memberId,
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
    public ResponseEntity<Void> delete(
            @PathVariable Long commentId,
            @SessionAttribute("LOGIN_MEMBER") Long memberId
    ) {

        commentService.delete(commentId, memberId);

        return ResponseEntity.ok().build();
    }
}
