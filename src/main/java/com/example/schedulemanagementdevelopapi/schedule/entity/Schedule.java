package com.example.schedulemanagementdevelopapi.schedule.entity;

import com.example.schedulemanagementdevelopapi.global.entity.SoftDeletableEntity;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends SoftDeletableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Column(columnDefinition = "longtext")
    private String content;

    public Schedule(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
