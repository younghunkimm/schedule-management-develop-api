package com.example.schedulemanagementdevelopapi.member.dto.response;

import com.example.schedulemanagementdevelopapi.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberSaveResponseDto (
        @Schema(description = "유저 번호")
        Long id,
        @Schema(description = "유저 이름")
        String name,
        @Schema(description = "유저 이메일")
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
