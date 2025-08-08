package com.example.schedulemanagementdevelopapi.schedule.repository;

import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    Optional<Schedule> findByIdAndDeletedAtIsNull(Long id);

    default Schedule findByIdOrElseThrow(Long id) {

        return findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제되었거나 존재하지 않는 일정입니다."));
    }
}
