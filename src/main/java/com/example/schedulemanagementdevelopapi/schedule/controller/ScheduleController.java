package com.example.schedulemanagementdevelopapi.schedule.controller;

import com.example.schedulemanagementdevelopapi.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.schedulemanagementdevelopapi.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulemanagementdevelopapi.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> save(
            @Valid @RequestBody ScheduleSaveRequestDto requestDto
    ) {

        ScheduleSaveResponseDto scheduleSaveResponseDto =
                scheduleService.save(
                        requestDto.getWriter(),
                        requestDto.getTitle(),
                        requestDto.getContent()
                );

        return ResponseEntity.ok(scheduleSaveResponseDto);
    }

}
