package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ScheduleUpdateResponseDto (
        @Schema(description = "일정 번호")
        Long id,
        @Schema(description = "작성자명")
        String writer,
        @Schema(description = "일정 제목")
        String title,
        @Schema(description = "일정 내용")
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ScheduleUpdateResponseDto from(Schedule schedule) {
        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getMember().getName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
