package com.example.schedulemanagementdevelopapi.member.dto.response;

import com.example.schedulemanagementdevelopapi.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberUpdateResponseDto (
        @Schema(description = "유저 이름")
        String name,
        @Schema(description = "유저 이메일")
        String email
) {

    public static MemberUpdateResponseDto from(Member member) {
        return new MemberUpdateResponseDto(
                member.getName(),
                member.getEmail()
        );
    }
}
