package com.example.schedulemanagementdevelopapi.comment.dto.response;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentSearchResponseDto(
        @Schema(description = "댓글 번호")
        Long id,
        @Schema(description = "작성자명")
        String writer,
        @Schema(description = "댓글 내용")
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static CommentSearchResponseDto from(Comment comment) {

        return CommentSearchResponseDto.builder()
                .id(comment.getId())
                .writer(comment.getMember().getName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
