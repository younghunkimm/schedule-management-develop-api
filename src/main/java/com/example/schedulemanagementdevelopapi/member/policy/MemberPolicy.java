package com.example.schedulemanagementdevelopapi.member.policy;

import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.exception.MemberErrorCode;
import org.springframework.stereotype.Component;

@Component
public class MemberPolicy {

    public void checkOwnerOrThrow(Member member, Long memberId) {
        if (false == member.isOwnedBy(memberId)) {
            throw new UnAuthorizedException(MemberErrorCode.ACCESS_DENIED);
        }
    }
}
