package com.example.schedulemanagementdevelopapi.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@Getter @Setter
@NoArgsConstructor
public class MemberSearchConditionDto {

    @Schema(description = "이름(부분 일치)", requiredMode = NOT_REQUIRED)
    private String name;

    @Schema(description = "이메일(부분 일치)", requiredMode = NOT_REQUIRED)
    private String email;
}
