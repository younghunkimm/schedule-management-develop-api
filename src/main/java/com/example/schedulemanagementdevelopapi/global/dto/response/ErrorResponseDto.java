package com.example.schedulemanagementdevelopapi.global.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDto (
        int status,
        String code,
        String message,
        String path,
        LocalDateTime timestamp,
        Map<String, String> data
) {

    public static ErrorResponseDto of(int status, String code, String message, String path) {
        return new ErrorResponseDto(status, code, message, path, LocalDateTime.now(), null);
    }

    public static ErrorResponseDto of(int status, String code, String message, String path, Map<String, String> data) {
        return new ErrorResponseDto(status, code, message, path, LocalDateTime.now(), data);
    }
}
