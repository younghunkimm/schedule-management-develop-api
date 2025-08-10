package com.example.schedulemanagementdevelopapi.schedule.exception;

import com.example.schedulemanagementdevelopapi.global.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ScheduleErrorCode implements ErrorType {

    // 4xx
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "SCHEDULE-001", "삭제되었거나 존재하지 않는 일정입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "SCHEDULE-002", "삭제할 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
