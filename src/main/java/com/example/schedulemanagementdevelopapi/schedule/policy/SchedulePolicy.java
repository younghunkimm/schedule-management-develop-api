package com.example.schedulemanagementdevelopapi.schedule.policy;

import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import com.example.schedulemanagementdevelopapi.schedule.exception.ScheduleErrorCode;
import org.springframework.stereotype.Component;

@Component
public class SchedulePolicy {

    public void checkOwnerOrThrow(Schedule schedule, Long memberId) {
        if (schedule.isOwnedBy(memberId)) {
            throw new UnAuthorizedException(ScheduleErrorCode.ACCESS_DENIED);
        }
    }
}
