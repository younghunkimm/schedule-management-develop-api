package com.example.schedulemanagementdevelopapi.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    @NotBlank(message = "일정의 제목은 필수값입니다.")
    @Size(max = 30, message = "일정 제목은 최대 30자입니다.")
    private String title;
}
