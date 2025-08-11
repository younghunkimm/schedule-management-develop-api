package com.example.schedulemanagementdevelopapi.comment.service;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSaveResponseDto;
import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import com.example.schedulemanagementdevelopapi.comment.repository.CommentRepository;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.repository.MemberRepository;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.example.schedulemanagementdevelopapi.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public CommentSaveResponseDto save(Long memberId, Long scheduleId, String content) {

        Member findMember = memberRepository.findByIdOrElseThrow(memberId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment comment = new Comment(findMember, findSchedule, content);
        Comment savedComment = commentRepository.save(comment);

        return CommentSaveResponseDto.from(savedComment);
    }

    @Override
    @Transactional
    public CommentUpdateResponseDto update(Long commentId, Long memberId, Long scheduleId, String content) {

        Comment findComment = commentRepository.findByIdAndMember_idAndSchedule_IdAndOrElseThrow(commentId, memberId, scheduleId);
        findComment.updateContent(content);

        return CommentUpdateResponseDto.from(findComment);
    }
}
