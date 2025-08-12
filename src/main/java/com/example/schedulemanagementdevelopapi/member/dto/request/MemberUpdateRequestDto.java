package com.example.schedulemanagementdevelopapi.member.dto.request;

import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberEmail;
import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
public class MemberUpdateRequestDto {

    @MemberName
    @Schema(description = "이름", example = "홍길동", requiredMode = REQUIRED)
    private String name;

    @MemberEmail
    @Schema(description = "이메일", example = "1234", requiredMode = REQUIRED)
    private String email;
}
