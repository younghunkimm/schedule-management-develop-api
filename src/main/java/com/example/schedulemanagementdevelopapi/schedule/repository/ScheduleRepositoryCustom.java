package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepositoryCustom {

    List<Schedule> search(ScheduleSearchConditionDto cond);
}
