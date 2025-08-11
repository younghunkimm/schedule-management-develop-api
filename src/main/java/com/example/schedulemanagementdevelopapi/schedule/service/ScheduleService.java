package com.example.schedulemanagementdevelopapi.schedule.service;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchDetailResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchSummaryResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleUpdateResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleSaveResponseDto save(Long memberId, String title, String content);

    List<ScheduleSearchSummaryResponseDto> searchWithCommentCount(ScheduleSearchConditionDto cond);

    ScheduleSearchDetailResponseDto findById(Long id);

    ScheduleUpdateResponseDto update(Long id, Long memberId, ScheduleUpdateRequestDto requestDto);

    void delete(Long id, Long memberId);
}
