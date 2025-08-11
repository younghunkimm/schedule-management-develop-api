package com.example.schedulemanagementdevelopapi.comment.policy;

import com.example.schedulemanagementdevelopapi.comment.entity.Comment;
import com.example.schedulemanagementdevelopapi.comment.exception.CommentErrorCode;
import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import org.springframework.stereotype.Component;

@Component
public class CommentPolicy {

    public void checkOwnerOrThrow(Comment comment, Long memberId) {
        if (comment.isOwnedBy(memberId)) {
            throw new UnAuthorizedException(CommentErrorCode.ACCESS_DENIED);
        }
    }
}
