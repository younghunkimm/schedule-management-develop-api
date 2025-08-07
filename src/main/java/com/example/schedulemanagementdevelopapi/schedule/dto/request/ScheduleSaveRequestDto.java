package com.example.schedulemanagementdevelopapi.schedule.dto.request;

import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    private String writer;

    private String title;

    private String content;
}
