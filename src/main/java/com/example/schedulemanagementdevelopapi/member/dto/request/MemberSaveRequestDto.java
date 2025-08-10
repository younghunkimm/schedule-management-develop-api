package com.example.schedulemanagementdevelopapi.member.dto.request;

import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberEmail;
import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberSaveRequestDto {

    @MemberName
    private String name;

    @MemberEmail
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String password;
}
