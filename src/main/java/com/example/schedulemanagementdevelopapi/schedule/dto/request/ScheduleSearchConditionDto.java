package com.example.schedulemanagementdevelopapi.schedule.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@Getter @Setter
@NoArgsConstructor
public class ScheduleSearchConditionDto {

    @Schema(description = "제목(부분 일치), 미지정 시 전체 조회", requiredMode = NOT_REQUIRED)
    private String title;
}
