package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;

public record ScheduleSaveResponseDto(
        Long id,
        String writer,
        String title,
        String content
) {

    public static ScheduleSaveResponseDto from(Schedule schedule) {
        return new ScheduleSaveResponseDto(
                schedule.getId(),
                schedule.getWriter(),
                schedule.getTitle(),
                schedule.getContent()
        );
    }
}
