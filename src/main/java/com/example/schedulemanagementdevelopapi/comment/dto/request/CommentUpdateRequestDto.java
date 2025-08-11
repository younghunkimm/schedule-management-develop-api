package com.example.schedulemanagementdevelopapi.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {

    @NotBlank(message = "일정의 내용은 필수값입니다.")
    private String content;
}
