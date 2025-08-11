package com.example.schedulemanagementdevelopapi.member.service;

import com.example.schedulemanagementdevelopapi.member.dto.request.MemberLoginRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberLoginResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberUpdateResponseDto;

import java.util.List;

public interface MemberService {
    MemberSaveResponseDto save(String name, String email, String password);

    List<MemberSearchResponseDto> search(MemberSearchConditionDto cond);

    MemberSearchResponseDto findById(Long memberId);

    MemberUpdateResponseDto update(Long memberId, Long authMemberId, MemberUpdateRequestDto requestDto);

    void delete(Long memberId, Long authMemberId);

    MemberLoginResponseDto login(MemberLoginRequestDto requestDto);
}
