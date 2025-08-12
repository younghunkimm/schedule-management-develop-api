package com.example.schedulemanagementdevelopapi.global.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDto (
        @Schema(description = "Http 응답 상태코드")
        int status,
        @Schema(description = "상세 상태코드")
        String code,
        @Schema(description = "에러 메세지")
        String message,
        @Schema(description = "에러 발생 경로")
        String path,
        LocalDateTime timestamp,
        @Schema(description = "추가 에러 정보")
        Map<String, String> data
) {

    public static ErrorResponseDto of(int status, String code, String message, String path) {
        return new ErrorResponseDto(status, code, message, path, LocalDateTime.now(), null);
    }

    public static ErrorResponseDto of(int status, String code, String message, String path, Map<String, String> data) {
        return new ErrorResponseDto(status, code, message, path, LocalDateTime.now(), data);
    }
}
