package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;

public record ScheduleUpdateResponseDto (
        String writer,
        String title
) {

    public static ScheduleUpdateResponseDto from(Schedule schedule) {
        return new ScheduleUpdateResponseDto(
                schedule.getWriter(),
                schedule.getTitle()
        );
    }
}
