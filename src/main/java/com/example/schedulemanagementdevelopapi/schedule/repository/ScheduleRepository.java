package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.global.exception.NotFoundException;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.example.schedulemanagementdevelopapi.schedule.exception.ScheduleErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    Optional<Schedule> findByIdAndDeletedAtIsNull(Long scheduleId);

    default Schedule findByIdOrElseThrow(Long scheduleId) {

        return findByIdAndDeletedAtIsNull(scheduleId).orElseThrow(() -> new NotFoundException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
    }

    default Schedule findWithMemberByIdOrElseThrow(Long scheduleId) {

        return findWithMemberById(scheduleId).orElseThrow(() -> new NotFoundException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
    }
}
