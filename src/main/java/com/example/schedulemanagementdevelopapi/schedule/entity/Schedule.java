package com.example.schedulemanagementdevelopapi.schedule.entity;

import com.example.schedulemanagementdevelopapi.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "일정의 작성자명은 필수값입니다.")
    private String writer;

    @NotBlank(message = "일정의 제목은 필수값입니다.")
    @Size(max = 30, message = "일정 제목은 최대 30자입니다.")
    private String title;

    @NotBlank(message = "일정의 내용은 필수값입니다.")
    @Column(columnDefinition = "longtext")
    private String content;

    public Schedule(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
