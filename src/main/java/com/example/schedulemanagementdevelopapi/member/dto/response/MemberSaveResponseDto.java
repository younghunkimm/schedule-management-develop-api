package com.example.schedulemanagementdevelopapi.member.dto.response;

import com.example.schedulemanagementdevelopapi.member.entity.Member;

public record MemberSaveResponseDto (
        Long id,
        String name,
        String email
) {

    public static MemberSaveResponseDto from(Member member) {
        return new MemberSaveResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }
}
