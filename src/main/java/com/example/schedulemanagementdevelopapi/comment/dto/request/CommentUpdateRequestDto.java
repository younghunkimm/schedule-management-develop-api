package com.example.schedulemanagementdevelopapi.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
public class CommentUpdateRequestDto {

    @NotBlank(message = "댓글의 내용은 필수값입니다.")
    @Schema(description = "댓글 내용", example = "수정할 댓글 내용", requiredMode = REQUIRED)
    private String content;
}
