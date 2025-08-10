package com.example.schedulemanagementdevelopapi.member.repository;

import com.example.schedulemanagementdevelopapi.global.exception.NotFoundException;
import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.member.exception.MemberErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByIdAndDeletedAtIsNull(Long id);

    default Member findByIdOrElseThrow(Long id) {

        return findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    Optional<Member> findByEmailAndDeletedAtIsNull(String email);

    default Member findByEmailOrElseThrow(String email) {

        return findByEmailAndDeletedAtIsNull(email).orElseThrow(() -> new UnAuthorizedException(MemberErrorCode.INVALID_EMAIL));
    }
}
