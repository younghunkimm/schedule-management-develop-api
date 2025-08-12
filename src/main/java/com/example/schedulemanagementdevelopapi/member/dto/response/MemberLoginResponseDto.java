package com.example.schedulemanagementdevelopapi.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberLoginResponseDto (
        @Schema(description = "유저 번호")
        Long id
) {}
