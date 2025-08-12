package com.example.schedulemanagementdevelopapi.schedule.service;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchDetailResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchSummaryResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleUpdateResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    ScheduleSaveResponseDto save(Long memberId, String title, String content);

    Page<ScheduleSearchSummaryResponseDto> searchPageWithCommentCount(ScheduleSearchConditionDto cond, Pageable pageable);

    ScheduleSearchDetailResponseDto findById(Long scheduleId);

    ScheduleUpdateResponseDto update(Long scheduleId, Long memberId, ScheduleUpdateRequestDto requestDto);

    void delete(Long scheduleId, Long memberId);
}
