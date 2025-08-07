package com.example.schedulemanagementdevelopapi.schedule.entity;

import com.example.schedulemanagementdevelopapi.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;

    private String title;

    @Column(columnDefinition = "longtext")
    private String content;

    public Schedule(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public void updateWriter(String writer) {
        this.writer = writer;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
}
