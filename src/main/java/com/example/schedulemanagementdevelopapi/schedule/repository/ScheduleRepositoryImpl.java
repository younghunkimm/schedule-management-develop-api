package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.entity.QSchedule;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QSchedule qSchedule = QSchedule.schedule;

    @Override
    public List<Schedule> search(ScheduleSearchConditionDto cond) {

        return queryFactory
                .selectFrom(qSchedule)
                .where(
                        eqWriter(cond.getWriter()),
                        containsTitle(cond.getTitle())
                )
                .orderBy(qSchedule.modifiedAt.desc())
                .fetch();
    }

    private BooleanExpression eqWriter(String writer) {
        return (StringUtils.hasText(writer))
                ? qSchedule.writer.eq(writer)
                : null;
    }

    private BooleanExpression containsTitle(String title) {
        return (StringUtils.hasText(title))
                ? qSchedule.title.contains(title)
                : null;
    }
}
