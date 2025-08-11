package com.example.schedulemanagementdevelopapi.schedule.dto.response;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSearchResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public record ScheduleSearchDetailResponseDto(
        Long id,
        String writer,
        String title,
        String content,
        List<CommentSearchResponseDto> comments,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ScheduleSearchDetailResponseDto from(Schedule schedule, List<CommentSearchResponseDto> comments) {
        return new ScheduleSearchDetailResponseDto(
                schedule.getId(),
                schedule.getMember().getName(),
                schedule.getTitle(),
                schedule.getContent(),
                comments,
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
