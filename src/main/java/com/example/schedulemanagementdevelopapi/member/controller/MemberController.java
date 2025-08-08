package com.example.schedulemanagementdevelopapi.member.controller;

import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSaveRequestDto;
import com.example.schedulemanagementdevelopapi.member.dto.request.MemberSearchConditionDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSaveResponseDto;
import com.example.schedulemanagementdevelopapi.member.dto.response.MemberSearchResponseDto;
import com.example.schedulemanagementdevelopapi.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberSaveResponseDto> save(
            @Valid @RequestBody MemberSaveRequestDto requestDto
    ) {

        MemberSaveResponseDto memberSaveResponseDto =
                memberService.save(
                        requestDto.getName(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        return ResponseEntity.ok(memberSaveResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<MemberSearchResponseDto>> search(
            @ModelAttribute MemberSearchConditionDto cond
    ) {

        return ResponseEntity.ok(memberService.search(cond));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberSearchResponseDto> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(memberService.findById(id));
    }

}
