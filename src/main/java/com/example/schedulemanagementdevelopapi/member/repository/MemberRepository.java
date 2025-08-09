package com.example.schedulemanagementdevelopapi.member.repository;

import com.example.schedulemanagementdevelopapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByIdAndDeletedAtIsNull(Long id);

    default Member findByIdOrElseThrow(Long id) {

        return findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제되었거나 존재하지 않는 유저입니다."));
    }

    Optional<Member> findByEmailAndDeletedAtIsNull(String email);

    default Member findByEmailOrElseThrow(String email) {

        return findByEmailAndDeletedAtIsNull(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "올바른 이메일이 아니거나 삭제된 유저입니다."));
    }
}
