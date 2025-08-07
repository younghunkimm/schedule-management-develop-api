package com.example.schedulemanagementdevelopapi.schedule.service;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.example.schedulemanagementdevelopapi.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto save(String writer, String title, String content) {

        Schedule schedule = new Schedule(writer, title, content);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleSaveResponseDto.from(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleSearchResponseDto> search(ScheduleSearchConditionDto cond) {

        return scheduleRepository.search(cond)
                .stream()
                .map(ScheduleSearchResponseDto::from)
                .toList();
    }
}
