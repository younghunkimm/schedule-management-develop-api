package com.example.schedulemanagementdevelopapi.comment.dto.response;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentSaveResponseDto(
        Long id,
        String writer,
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
