package com.example.schedulemanagementdevelopapi.member.dto.request;

import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberEmail;
import com.example.schedulemanagementdevelopapi.member.validation.annotation.MemberName;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    @MemberName
    private String name;

    @MemberEmail
    private String email;
}
