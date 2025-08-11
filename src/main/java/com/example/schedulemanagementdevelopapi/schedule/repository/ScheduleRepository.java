package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.global.exception.NotFoundException;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.example.schedulemanagementdevelopapi.schedule.exception.ScheduleErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    Optional<Schedule> findByIdAndDeletedAtIsNull(Long id);

    default Schedule findByIdOrElseThrow(Long id) {

        return findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
    }

    default Schedule findWithMemberByIdOrElseThrow(Long id) {

        return findWithMemberById(id).orElseThrow(() -> new NotFoundException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
    }
}
