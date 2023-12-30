package com.example.controller;

import com.example.service.MemberService;
import com.example.service.dto.request.CreateMemberRequest;
import com.example.service.dto.request.UpdateMemberRequest;
import com.example.service.dto.response.ReadMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMember(@RequestBody CreateMemberRequest request) {
        Long memberNo = memberService.createMember(request);
        URI uri = URI.create(String.format("/v1/members/%d", memberNo));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{memberNo}")
    public ResponseEntity<ReadMemberResponse> readMember(@PathVariable Long memberNo) {
        return ResponseEntity.ok(memberService.readMember(memberNo));
    }

    @PutMapping("/{memberNo}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberNo,
            @RequestBody UpdateMemberRequest request
    ) {
        memberService.updateMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberNo}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberNo) {
        memberService.deleteMember(memberNo);
        return ResponseEntity.noContent().build();
    }
}
