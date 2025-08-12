package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(description = "일정 생성 Response DTO")
public record ScheduleSaveResponseDto(
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

    public static ScheduleSaveResponseDto from(Schedule schedule) {

        return ScheduleSaveResponseDto.builder()
                .id(schedule.getId())
                .writer(schedule.getMember().getName())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
    }
}
