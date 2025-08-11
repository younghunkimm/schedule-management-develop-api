package com.example.schedulemanagementdevelopapi.schedule.service;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSearchResponseDto;
import com.example.schedulemanagementdevelopapi.comment.repository.CommentRepository;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.repository.MemberRepository;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchDetailResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchSummaryResponseDto;
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
    private final CommentRepository commentRepository;

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
    public List<ScheduleSearchSummaryResponseDto> searchWithCommentCount(ScheduleSearchConditionDto cond) {

        return scheduleRepository.searchWithCommentCount(cond);
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleSearchDetailResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        List<CommentSearchResponseDto> findCommentList = commentRepository.findAllByScheduleId(findSchedule.getId());

        return ScheduleSearchDetailResponseDto.from(findSchedule, findCommentList);
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
