package com.example.schedulemanagementdevelopapi.comment.repository;

import com.example.schedulemanagementdevelopapi.comment.dto.response.CommentSearchResponseDto;
import com.example.schedulemanagementdevelopapi.comment.entity.QComment;
import com.example.schedulemanagementdevelopapi.member.entity.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentSearchResponseDto> findAllByScheduleId(Long scheduleId) {

        QComment qComment = QComment.comment;
        QMember qMember = QMember.member;

        return queryFactory
                .select(Projections.constructor(
                        CommentSearchResponseDto.class,
                        qComment.id,
                        qMember.name,
                        qComment.content,
                        qComment.createdAt,
                        qComment.modifiedAt
                ))
                .from(qComment)
                .join(qComment.member, qMember)
                .where(
                        qComment.schedule.id.eq(scheduleId),
                        qComment.deletedAt.isNull()
                )
                .orderBy(qComment.modifiedAt.desc())
                .fetch();
    }
}
