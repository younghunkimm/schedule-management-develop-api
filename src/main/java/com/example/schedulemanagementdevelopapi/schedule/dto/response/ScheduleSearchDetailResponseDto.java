package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSearchResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ScheduleSearchDetailResponseDto(
        @Schema(description = "일정 번호")
        Long id,
        @Schema(description = "작성자명")
        String writer,
        @Schema(description = "일정 제목")
        String title,
        @Schema(description = "일정 내용")
        String content,
        @Schema(description = "댓글 리스트")
        List<CommentSearchResponseDto> comments,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ScheduleSearchDetailResponseDto from(Schedule schedule, List<CommentSearchResponseDto> comments) {

        return ScheduleSearchDetailResponseDto.builder()
                .id(schedule.getId())
                .writer(schedule.getMember().getName())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .comments(comments)
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
    }
}
