package com.example.schedulemanagementdevelopapi.comment.repository;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSearchResponseDto;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentSearchResponseDto> findAllByScheduleId(Long scheduleId);
}
