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

    ScheduleSearchDetailResponseDto findById(Long id);

    ScheduleUpdateResponseDto update(Long id, Long memberId, ScheduleUpdateRequestDto requestDto);

    void delete(Long id, Long memberId);
}
