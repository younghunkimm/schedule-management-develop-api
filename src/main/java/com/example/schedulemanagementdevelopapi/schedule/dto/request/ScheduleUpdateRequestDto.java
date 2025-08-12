package com.example.schedulemanagementdevelopapi.schedule.dto.request;

import com.example.schedulemanagementdevelopapi.schedule.validation.annotation.ScheduleTitle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
public class ScheduleUpdateRequestDto {

    @ScheduleTitle
    @Schema(description = "수정할 일정 제목", requiredMode = REQUIRED)
    private String title;

    @NotBlank(message = "일정의 내용은 필수값입니다.")
    @Schema(description = "수정할 일정 내용", requiredMode = REQUIRED)
    private String content;
}
