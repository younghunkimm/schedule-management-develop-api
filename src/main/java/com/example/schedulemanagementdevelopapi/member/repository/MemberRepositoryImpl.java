package com.example.schedulemanagementdevelopapi.member.repository;

import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QMember qMember = QMember.member;

    @Override
    public List<Member> search(MemberSearchConditionDto cond) {

        return queryFactory
                .selectFrom(qMember)
                .where(
                        containsName(cond.getName()),
                        containsEmail(cond.getEmail())
                )
                .where(qMember.deletedAt.isNull())
                .orderBy(qMember.modifiedAt.desc())
                .fetch();
    }

    private BooleanExpression containsName(String name) {
        return (StringUtils.hasText(name))
                ? qMember.name.containsIgnoreCase(name)
                : null;
    }

    private BooleanExpression containsEmail(String email) {
        return (StringUtils.hasText(email))
                ? qMember.email.contains(email)
                : null;
    }
}
