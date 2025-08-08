package com.example.schedulemanagementdevelopapi.member.service;

import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;

import java.util.List;

public interface MemberService {
    MemberSaveResponseDto save(String name, String email, String password);

    List<MemberSearchResponseDto> search(MemberSearchConditionDto cond);
}
