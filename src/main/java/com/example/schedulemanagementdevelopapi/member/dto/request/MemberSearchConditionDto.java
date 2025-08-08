package com.example.schedulemanagementdevelopapi.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberSearchConditionDto {

    private String name;

    private String email;
}
