package com.example.schedulemanagementdevelopapi.comment.dto.response;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentUpdateResponseDto(
        Long id,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static CommentUpdateResponseDto from(Comment comment) {
        return new CommentUpdateResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
