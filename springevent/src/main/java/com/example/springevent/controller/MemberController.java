package com.example.springevent.controller;

import com.example.springevent.service.MemberService;
import com.example.springevent.service.dto.CreateMemberRequest;
import com.example.springevent.service.dto.ReadMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<ReadMemberResponse>> readMembers() {
        return ResponseEntity.ok(memberService.readMembers());
    }

    @PostMapping
    public ResponseEntity<Long> createMember(@RequestBody CreateMemberRequest request) {
        return ResponseEntity.ok(memberService.createMember(request.getMemberName(), request.getAge()));
    }
}
