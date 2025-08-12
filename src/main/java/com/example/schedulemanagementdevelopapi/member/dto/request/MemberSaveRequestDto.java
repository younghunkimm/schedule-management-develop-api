package com.example.schedulemanagementdevelopapi.member.dto.request;

import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberEmail;
import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
public class MemberSaveRequestDto {

    @MemberName
    @Schema(description = "이름", example = "홍길동", requiredMode = REQUIRED)
    private String name;

    @MemberEmail
    @Schema(description = "이메일/아이디", example = "test@google.com", requiredMode = REQUIRED)
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    @Schema(description = "비밀번호", example = "1234", requiredMode = REQUIRED)
    private String password;
}
