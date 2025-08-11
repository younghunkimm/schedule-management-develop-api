package com.example.schedulemanagementdevelopapi.comment.exception;

import com.example.schedulemanagementdevelopapi.global.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorType {

    // 4xx
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT-001", "삭제되었거나 존재하지 않는 댓글입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "COMMENT-002", "권한이 없습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
