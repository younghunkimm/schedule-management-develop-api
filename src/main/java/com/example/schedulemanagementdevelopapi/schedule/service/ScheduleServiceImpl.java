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
import com.example.schedulemanagementdevelopapi.schedule.policy.SchedulePolicy;
import com.example.schedulemanagementdevelopapi.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    private final SchedulePolicy schedulePolicy;

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
    public Page<ScheduleSearchSummaryResponseDto> searchPageWithCommentCount(
            ScheduleSearchConditionDto cond,
            Pageable pageable
    ) {

        return scheduleRepository.searchPageWithCommentCount(cond, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleSearchDetailResponseDto findById(Long scheduleId) {

        Schedule findSchedule = scheduleRepository.findWithMemberByIdOrElseThrow(scheduleId);
        List<CommentSearchResponseDto> findCommentList = commentRepository.findAllByScheduleId(findSchedule.getId());

        return ScheduleSearchDetailResponseDto.from(findSchedule, findCommentList);
    }

    @Override
    @Transactional
    public ScheduleUpdateResponseDto update(Long scheduleId, Long memberId, ScheduleUpdateRequestDto requestDto) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        schedulePolicy.checkOwnerOrThrow(findSchedule, memberId);

        findSchedule.updateTitle(requestDto.getTitle());
        findSchedule.updateContent(requestDto.getContent());

        return ScheduleUpdateResponseDto.from(findSchedule);
    }

    @Override
    @Transactional
    public void delete(Long scheduleId, Long memberId) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        schedulePolicy.checkOwnerOrThrow(findSchedule, memberId);

        findSchedule.softDelete();
    }
}
