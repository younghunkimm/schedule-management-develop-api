package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.global.exception.NotFoundException;
import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.example.schedulemanagementdevelopapi.schedule.exception.ScheduleErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    Optional<Schedule> findByIdAndDeletedAtIsNull(Long id);

    default Schedule findByIdOrElseThrow(Long id) {

        return findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
    }

    // 연관관계 Member Entity의 id(Member -> @Id의 필드명) 필드를 찾기 위해 '_' 를 사용
    // 만약 Member Entity의 @Id가 붙은 PK의 필드명이 key로 바뀐다면 findByIdAndMember_KeyAnd... 로 바꿔야함
    // 그냥 MemberId 하게 되면 Schedule Entity의 memberId 필드를 찾기 때문에 의미가 다름
    Optional<Schedule> findByIdAndMember_IdAndDeletedAtIsNull(Long id, Long memberId);

    default Schedule findByIdAndMember_IdOrElseThrow(Long id, Long memberId) {

        return findByIdAndMember_IdAndDeletedAtIsNull(id, memberId).orElseThrow(() -> new UnAuthorizedException(ScheduleErrorCode.ACCESS_DENIED));
    }
}
