package com.example.schedulemanagementdevelopapi.member.dto.request;

import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
public class MemberLoginRequestDto {

    @MemberEmail
    @Schema(description = "이메일", example = "kyh@google.com", requiredMode = REQUIRED)
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Schema(description = "비밀번호", example = "1234", requiredMode = REQUIRED)
    private String password;
}
