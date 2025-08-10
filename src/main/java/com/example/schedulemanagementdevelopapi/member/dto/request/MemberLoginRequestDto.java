package com.example.schedulemanagementdevelopapi.member.dto.request;

import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginRequestDto {

    @MemberEmail
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;
}
