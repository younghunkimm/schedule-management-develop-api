package com.example.schedulemanagementdevelopapi.comment.dto.response;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentUpdateResponseDto(
        @Schema(description = "댓글 번호")
        Long id,
        @Schema(description = "댓글 내용")
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static CommentUpdateResponseDto from(Comment comment) {

        return CommentUpdateResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
