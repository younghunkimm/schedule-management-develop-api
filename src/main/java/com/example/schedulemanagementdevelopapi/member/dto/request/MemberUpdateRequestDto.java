package com.example.schedulemanagementdevelopapi.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    @NotBlank(message = "이름은 필수값입니다.")
    @Size(max = 4, message = "이름은 최대 4자입니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수값입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
}
