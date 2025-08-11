package com.example.schedulemanagementdevelopapi.comment.service;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSaveResponseDto;

public interface CommentService {

    CommentSaveResponseDto save(Long memberId, Long scheduleId, String content);
}
