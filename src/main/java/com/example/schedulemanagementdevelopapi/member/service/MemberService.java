package com.example.schedulemanagementdevelopapi.member.service;

import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;

public interface MemberService {
    MemberSaveResponseDto save(String name, String email, String password);
}
