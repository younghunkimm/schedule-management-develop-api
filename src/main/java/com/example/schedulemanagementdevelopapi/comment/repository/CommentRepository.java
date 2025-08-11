package com.example.schedulemanagementdevelopapi.comment.repository;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
