package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ScheduleSearchSummaryResponseDto(
        @Schema(description = "일정 번호")
        Long id,
        @Schema(description = "작성자명")
        String writer,
        @Schema(description = "일정 제목")
        String title,
        @Schema(description = "일정 내용")
        String content,
        @Schema(description = "댓글 수")
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
