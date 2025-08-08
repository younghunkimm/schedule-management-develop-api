package com.example.schedulemanagementdevelopapi.member.repository;

import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> search(MemberSearchConditionDto cond);
}
