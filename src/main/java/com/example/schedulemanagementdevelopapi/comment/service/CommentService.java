package com.example.schedulemanagementdevelopapi.comment.service;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSaveResponseDto;
import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentUpdateResponseDto;

public interface CommentService {

    CommentSaveResponseDto save(Long memberId, Long scheduleId, String content);

    CommentUpdateResponseDto update(Long commentId, Long memberId, String content);

    void delete(Long commentId, Long memberId);
}
