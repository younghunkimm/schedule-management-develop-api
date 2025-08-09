package com.example.schedulemanagementdevelopapi.schedule.service;

import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.repository.MemberRepository;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleUpdateResponseDto;
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
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ScheduleSaveResponseDto save(Long memberId, String title, String content) {

        Member findMember = memberRepository.findByIdOrElseThrow(memberId);

        Schedule schedule = new Schedule(findMember, title, content);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleSaveResponseDto.from(savedSchedule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleSearchResponseDto> search(ScheduleSearchConditionDto cond) {

        return scheduleRepository.search(cond)
                .stream()
                .map(ScheduleSearchResponseDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleSearchResponseDto findById(Long id) {

        return ScheduleSearchResponseDto.from(scheduleRepository.findByIdOrElseThrow(id));
    }

    @Override
    @Transactional
    public ScheduleUpdateResponseDto update(Long id, Long memberId, ScheduleUpdateRequestDto requestDto) {

        Schedule findSchedule = scheduleRepository.findByIdAndMember_IdOrElseThrow(id, memberId);
        findSchedule.updateTitle(requestDto.getTitle());
        findSchedule.updateContent(requestDto.getContent());

        return ScheduleUpdateResponseDto.from(findSchedule);
    }

    @Override
    @Transactional
    public void delete(Long id, Long memberId) {

        Schedule findSchedule = scheduleRepository.findByIdAndMember_IdOrElseThrow(id, memberId);

        findSchedule.softDelete();
    }
}
