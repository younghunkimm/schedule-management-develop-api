package com.example.schedulemanagementdevelopapi.member.repository;

import com.example.schedulemanagementdevelopapi.schedule.entity.QSchedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QSchedule schedule = QSchedule.schedule;

}
