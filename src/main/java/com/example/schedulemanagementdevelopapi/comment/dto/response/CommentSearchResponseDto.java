package com.example.schedulemanagementdevelopapi.comment.dto.response;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentSearchResponseDto(
        Long id,
        String writer,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static CommentSearchResponseDto from(Comment comment) {

        return new CommentSearchResponseDto(
                comment.getId(),
                comment.getMember().getName(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
