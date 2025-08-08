package com.example.schedulemanagementdevelopapi.member.dto.response;

import com.example.schedulemanagementdevelopapi.member.entity.Member;

public record MemberUpdateResponseDto (
        String name,
        String email
) {

    public static MemberUpdateResponseDto from(Member member) {
        return new MemberUpdateResponseDto(
                member.getName(),
                member.getEmail()
        );
    }
}
