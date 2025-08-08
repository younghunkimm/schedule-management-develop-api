package com.example.schedulemanagementdevelopapi.member.service;

import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberSaveResponseDto save(String name, String email, String password) {

        Member member = new Member(name, email, password);
        Member savedMember = memberRepository.save(member);

        return MemberSaveResponseDto.from(savedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberSearchResponseDto> search(MemberSearchConditionDto cond) {

        return memberRepository.search(cond)
                .stream()
                .map(MemberSearchResponseDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberSearchResponseDto findById(Long id) {

        return MemberSearchResponseDto.from(memberRepository.findByIdOrElseThrow(id));
    }
}
