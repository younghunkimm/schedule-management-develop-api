package com.example.schedulemanagementdevelopapi.schedule.dto.request;

import com.example.schedulemanagementdevelopapi.schedule.validation.annotation.ScheduleTitle;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    @ScheduleTitle
    private String title;

    @NotBlank(message = "일정의 내용은 필수값입니다.")
    private String content;
}
