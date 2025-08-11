package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchSummaryResponseDto;

import java.util.List;

public interface ScheduleRepositoryCustom {

    List<ScheduleSearchSummaryResponseDto> searchWithCommentCount(ScheduleSearchConditionDto cond);
}
