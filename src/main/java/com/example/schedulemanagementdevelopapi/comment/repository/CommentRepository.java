package com.example.schedulemanagementdevelopapi.comment.repository;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import com.example.schedulemanagementdevelopapi.comment.exception.CommentErrorCode;
import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    Optional<Comment> findByIdAndMember_IdAndSchedule_IdAndDeletedAtIsNull(Long commentId, Long memberId, Long scheduleId);

    default Comment findByIdAndMember_idAndSchedule_IdAndOrElseThrow(Long commentId, Long memberId, Long scheduleId) {

        return findByIdAndMember_IdAndSchedule_IdAndDeletedAtIsNull(commentId, memberId, scheduleId)
                .orElseThrow(() -> new UnAuthorizedException(CommentErrorCode.ACCESS_DENIED));
    }

    List<Comment> findCommentsByScheduleIdAndDeletedAtIsNullOrderByModifiedAtDesc(Long scheduleId);
}
