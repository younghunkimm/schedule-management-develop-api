package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.comment.entity.QComment;
import com.example.schedulemanagementdevelopapi.member.entity.QMember;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchSummaryResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.entity.QSchedule;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Schedule> findWithMemberById(Long id) {

        QSchedule qSchedule = QSchedule.schedule;

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(qSchedule)
                        .join(qSchedule.member).fetchJoin()
                        .where(
                                qSchedule.id.eq(id),
                                qSchedule.deletedAt.isNull()
                        )
                        .fetchOne()
        );
    }

    @Override
    public List<ScheduleSearchSummaryResponseDto> searchWithCommentCount(ScheduleSearchConditionDto cond) {
        QSchedule qSchedule = QSchedule.schedule;
        QComment qComment = QComment.comment;
        QMember qMember = QMember.member;

        return queryFactory
                .select(
                        Projections.constructor(ScheduleSearchSummaryResponseDto.class,
                        qSchedule.id,
                        qMember.name,
                        qSchedule.title,
                        qSchedule.content,
                        JPAExpressions
                                .select(qComment.id.count())
                                .from(qComment)
                                .where(
                                        qComment.schedule.id.eq(qSchedule.id),
                                        qComment.deletedAt.isNull()
                                ),
                        qSchedule.createdAt,
                        qSchedule.modifiedAt
                ))
                .from(qSchedule)
                .leftJoin(qSchedule.member, qMember)
                .where(
                        qSchedule.deletedAt.isNull(),
                        containsTitle(cond.getTitle())
                )
                .orderBy(qSchedule.modifiedAt.desc())
                .fetch();
    }

    private BooleanExpression containsTitle(String title) {
        QSchedule qSchedule = QSchedule.schedule;

        return (StringUtils.hasText(title))
                ? qSchedule.title.contains(title)
                : null;
    }
}
