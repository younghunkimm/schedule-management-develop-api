package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleSearchResponseDto(
        Long id,
        String writer,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) {

    public static ScheduleSearchResponseDto from(Schedule schedule) {
        return new ScheduleSearchResponseDto(
                schedule.getId(),
                schedule.getWriter(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
