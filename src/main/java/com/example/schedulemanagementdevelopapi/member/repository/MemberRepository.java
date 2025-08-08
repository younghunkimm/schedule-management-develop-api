package com.example.schedulemanagementdevelopapi.member.repository;

import com.example.schedulemanagementdevelopapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
