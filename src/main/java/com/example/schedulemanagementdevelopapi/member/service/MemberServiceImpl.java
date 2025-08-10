package com.example.schedulemanagementdevelopapi.member.service;

import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberLoginRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberLoginResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.exception.MemberErrorCode;
import com.example.schedulemanagementdevelopapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {

        Member findMember = memberRepository.findByEmailOrElseThrow(requestDto.getEmail());

        if (false == ObjectUtils.nullSafeEquals(findMember.getPassword(), requestDto.getPassword())) {
            throw new UnAuthorizedException(MemberErrorCode.INVALID_PASSWORD);
        }

        return new MemberLoginResponseDto(findMember.getId());
    }

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

    @Override
    @Transactional
    public MemberUpdateResponseDto update(Long id, MemberUpdateRequestDto requestDto) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);
        findMember.updateName(requestDto.getName());
        findMember.updateEmail(requestDto.getEmail());

        return MemberUpdateResponseDto.from(findMember);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        findMember.softDelete();
    }
}
