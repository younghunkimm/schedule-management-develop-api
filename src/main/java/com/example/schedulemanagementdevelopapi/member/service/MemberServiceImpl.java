package com.example.schedulemanagementdevelopapi.member.service;

import com.example.schedulemanagementdevelopapi.global.config.PasswordEncoder;
import com.example.schedulemanagementdevelopapi.global.exception.BusinessException;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberLoginRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberLoginResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.exception.MemberErrorCode;
import com.example.schedulemanagementdevelopapi.member.policy.MemberPolicy;
import com.example.schedulemanagementdevelopapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final MemberPolicy memberPolicy;

    @Override
    @Transactional(readOnly = true)
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {

        Member findMember = memberRepository.findByEmailOrElseThrow(requestDto.getEmail());

        findMember.verifyPasswordOrThrow(passwordEncoder, requestDto.getPassword());

        return new MemberLoginResponseDto(findMember.getId());
    }

    @Override
    @Transactional
    public MemberSaveResponseDto save(String name, String email, String password) {

        if (memberRepository.existsByEmail(email)) {
            throw new BusinessException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(name, email, encodedPassword);
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
    public MemberSearchResponseDto findById(Long memberId) {

        return MemberSearchResponseDto.from(memberRepository.findByIdOrElseThrow(memberId));
    }

    @Override
    @Transactional
    public MemberUpdateResponseDto update(Long memberId, Long authMemberId, MemberUpdateRequestDto requestDto) {

        Member findMember = memberRepository.findByIdOrElseThrow(memberId);

        memberPolicy.checkOwnerOrThrow(findMember, authMemberId);

        findMember.updateName(requestDto.getName());
        findMember.updateEmail(requestDto.getEmail());

        return MemberUpdateResponseDto.from(findMember);
    }

    @Override
    @Transactional
    public void delete(Long memberId, Long authMemberId) {

        Member findMember = memberRepository.findByIdOrElseThrow(memberId);

        memberPolicy.checkOwnerOrThrow(findMember, authMemberId);

        findMember.softDelete();
    }
}
