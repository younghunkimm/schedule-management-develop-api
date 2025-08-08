package com.example.schedulemanagementdevelopapi.member.dto.response;

import com.example.schedulemanagementdevelopapi.member.entity.Member;

import java.time.LocalDateTime;

public record MemberSearchResponseDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static MemberSearchResponseDto from(Member member) {
        return new MemberSearchResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }
}
