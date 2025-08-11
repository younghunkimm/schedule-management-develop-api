package com.example.schedulemanagementdevelopapi.comment.repository;

import com.example.schedulemanagementdevelopapi.comment.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QComment qComment = QComment.comment;
}
