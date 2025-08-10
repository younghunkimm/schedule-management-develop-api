package com.example.schedulemanagementdevelopapi.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorType {

    // 4xx
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "COMMON-VAL-001", "요청 값이 유효하지 않습니다."),

    // 5xx
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-000", "서버 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
