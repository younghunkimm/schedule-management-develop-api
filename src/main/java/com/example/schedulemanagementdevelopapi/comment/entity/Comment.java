package com.example.schedulemanagementdevelopapi.comment.entity;

import com.example.schedulemanagementdevelopapi.global.entity.SoftDeletableEntity;
import com.example.schedulemanagementdevelopapi.member.entity.Member;
import com.example.schedulemanagementdevelopapi.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends SoftDeletableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column(columnDefinition = "longtext")
    private String content;

    public Comment(Member member, Schedule schedule, String content) {
        this.member = member;
        this.schedule = schedule;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
