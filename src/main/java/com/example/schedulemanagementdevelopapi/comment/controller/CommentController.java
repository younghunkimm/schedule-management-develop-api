package com.example.schedulemanagementdevelopapi.comment.controller;

import com.example.schedulemanagementdevelopapi.comment.dto.request.CommentSaveRequestDto;
import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSaveResponseDto;
import com.example.schedulemanagementdevelopapi.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
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
}
