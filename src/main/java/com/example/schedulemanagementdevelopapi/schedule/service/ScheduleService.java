package com.example.schedulemanagementdevelopapi.schedule.service;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleUpdateResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleSaveResponseDto save(Long memberId, String title, String content);

    List<ScheduleSearchResponseDto> search(ScheduleSearchConditionDto cond);

    ScheduleSearchResponseDto findById(Long id);

    ScheduleUpdateResponseDto update(Long id, ScheduleUpdateRequestDto requestDto);

    void delete(Long id);
}
