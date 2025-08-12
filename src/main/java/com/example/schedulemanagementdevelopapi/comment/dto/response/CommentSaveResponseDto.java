package com.example.schedulemanagementdevelopapi.comment.dto.response;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommentSaveResponseDto(
        @Schema(description = "댓글 번호")
        Long id,
        @Schema(description = "작성자명")
        String writer,
        @Schema(description = "댓글 내용")
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static CommentSaveResponseDto from(Comment comment) {

        return new CommentSaveResponseDto(
                comment.getId(),
                comment.getMember().getName(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
