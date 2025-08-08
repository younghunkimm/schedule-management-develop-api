package com.example.schedulemanagementdevelopapi.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    @NotNull(message = "작성자 정보가 누락되었습니다.")
    private Long memberId;

    @NotBlank(message = "일정의 제목은 필수값입니다.")
    @Size(max = 30, message = "일정 제목은 최대 30자입니다.")
    private String title;

    @NotBlank(message = "일정의 내용은 필수값입니다.")
    private String content;
}
