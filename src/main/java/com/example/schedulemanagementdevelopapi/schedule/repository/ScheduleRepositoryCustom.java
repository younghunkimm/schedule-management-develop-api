package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchSummaryResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScheduleRepositoryCustom {

    Optional<Schedule> findWithMemberById(Long scheduleId);

    Page<ScheduleSearchSummaryResponseDto> searchPageWithCommentCount(ScheduleSearchConditionDto cond, Pageable pageable);
}
