package com.example.schedulemanagementdevelopapi.member.dto.response;

import com.example.schedulemanagementdevelopapi.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberSearchResponseDto(
        @Schema(description = "유저 번호")
        Long id,
        @Schema(description = "유저 이름")
        String name,
        @Schema(description = "유저 이메일")
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static MemberSearchResponseDto from(Member member) {

        return MemberSearchResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }
}
