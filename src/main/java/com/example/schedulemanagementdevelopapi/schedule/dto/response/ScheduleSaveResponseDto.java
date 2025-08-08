package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleSaveResponseDto(
        Long id,
        String writer,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ScheduleSaveResponseDto from(Schedule schedule) {
        return new ScheduleSaveResponseDto(
                schedule.getId(),
                schedule.getMember().getName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
