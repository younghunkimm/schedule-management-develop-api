package com.example.schedulemanagementdevelopapi.member.entity;

import com.example.schedulemanagementdevelopapi.global.config.PasswordEncoder;
import com.example.schedulemanagementdevelopapi.global.entity.SoftDeletableEntity;
import com.example.schedulemanagementdevelopapi.global.exception.UnAuthorizedException;
import com.example.schedulemanagementdevelopapi.member.exception.MemberErrorCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Entity
@Getter
@NoArgsConstructor
public class Member extends SoftDeletableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean isOwnedBy(Long memberId) {

        return ObjectUtils.nullSafeEquals(this.id, memberId);
    }

    public void verifyPasswordOrThrow(PasswordEncoder encoder, String rawPassword) {

        if (false == encoder.matches(rawPassword, password)) {
            throw new UnAuthorizedException(MemberErrorCode.INVALID_PASSWORD);
        }
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

}
