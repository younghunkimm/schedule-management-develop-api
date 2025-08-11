package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleSearchSummaryResponseDto(
        Long id,
        String writer,
        String title,
        String content,
        long commentCount,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ScheduleSearchSummaryResponseDto from(Schedule schedule, long commentCount) {
        return new ScheduleSearchSummaryResponseDto(
                schedule.getId(),
                schedule.getMember().getName(),
                schedule.getTitle(),
                schedule.getContent(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
