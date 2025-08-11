package com.example.schedulemanagementdevelopapi.comment.repository;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import com.example.schedulemanagementdevelopapi.comment.exception.CommentErrorCode;
import com.example.schedulemanagementdevelopapi.global.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    Optional<Comment> findByIdAndDeletedAtIsNull(Long commentId);

    default Comment findByIdOrElseThrow(Long commentId) {

        return findByIdAndDeletedAtIsNull(commentId).orElseThrow(() -> new NotFoundException(CommentErrorCode.COMMENT_NOT_FOUND));
    }
}
