package com.example.schedulemanagementdevelopapi.schedule.service;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleSaveResponseDto save(String writer, String title, String content);

    List<ScheduleSearchResponseDto> search(ScheduleSearchConditionDto cond);

    ScheduleSearchResponseDto findById(Long id);
}
