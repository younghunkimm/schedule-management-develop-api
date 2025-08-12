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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<ScheduleSearchSummaryResponseDto> searchPageWithCommentCount(
            ScheduleSearchConditionDto cond,
            Pageable pageable
    ) {
        QSchedule qSchedule = QSchedule.schedule;
        QComment qComment = QComment.comment;
        QMember qMember = QMember.member;

        List<ScheduleSearchSummaryResponseDto> content = queryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(queryFactory
                .select(qSchedule.count())
                .from(qSchedule)
                .where(
                        qSchedule.deletedAt.isNull(),
                        containsTitle(cond.getTitle())
                )
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression containsTitle(String title) {
        QSchedule qSchedule = QSchedule.schedule;

        return (StringUtils.hasText(title))
                ? qSchedule.title.contains(title)
                : null;
    }
}
