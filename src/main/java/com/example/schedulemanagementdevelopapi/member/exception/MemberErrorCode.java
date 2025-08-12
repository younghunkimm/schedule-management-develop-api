package com.example.schedulemanagementdevelopapi.member.exception;

import com.example.schedulemanagementdevelopapi.global.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorType {

    // 4xx
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-001", "삭제되었거나 존재하지 않는 사용자입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "MEMBER-002", "권한이 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER-003", "비밀번호가 틀렸습니다."),
    INVALID_EMAIL(HttpStatus.UNAUTHORIZED, "MEMBER-004", "이메일이 올바르지 않습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER-005", "이미 사용 중인 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
