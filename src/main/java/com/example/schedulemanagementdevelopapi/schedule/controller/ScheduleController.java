package com.example.schedulemanagementdevelopapi.schedule.controller;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSearchConditionDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSearchResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleUpdateResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> save(
            @SessionAttribute("LOGIN_MEMBER") Long memberId,
            @Valid @RequestBody ScheduleSaveRequestDto requestDto
    ) {

        ScheduleSaveResponseDto scheduleSaveResponseDto =
                scheduleService.save(
                        memberId,
                        requestDto.getTitle(),
                        requestDto.getContent()
                );

        return ResponseEntity.ok(scheduleSaveResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleSearchResponseDto>> search(
            @ModelAttribute ScheduleSearchConditionDto cond
    ) {

        return ResponseEntity.ok(scheduleService.search(cond));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleSearchResponseDto> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleUpdateResponseDto> update(
            @PathVariable Long id,
            @SessionAttribute("LOGIN_MEMBER") Long memberId,
            @Valid @RequestBody ScheduleUpdateRequestDto requestDto
    ) {

        return ResponseEntity.ok(scheduleService.update(id, memberId, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @SessionAttribute("LOGIN_MEMBER") Long memberId
    ) {

        scheduleService.delete(id, memberId);

        return ResponseEntity.ok().build();
    }

}
