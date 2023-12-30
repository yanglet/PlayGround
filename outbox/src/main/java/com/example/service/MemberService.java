package com.example.service;

import com.example.repository.Member;
import com.example.repository.MemberRepository;
import com.example.repository.MemberUpdateEventQueue;
import com.example.repository.MemberUpdateEventQueueRepository;
import com.example.service.dto.request.CreateMemberRequest;
import com.example.service.dto.response.ReadMemberResponse;
import com.example.service.dto.request.UpdateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberUpdateEventQueueRepository memberUpdateEventQueueRepository;

    @Transactional
    public Long createMember(CreateMemberRequest request) {
        Member member = Member.of(request.getName(), request.getAge());
        memberRepository.save(member);
        return member.getMemberNo();
    }

    @Transactional(readOnly = true)
    public ReadMemberResponse readMember(Long memberNo) {
        Member member = memberRepository.findById(memberNo).orElseThrow(RuntimeException::new);
        return ReadMemberResponse.from(member);
    }

    @Transactional
    public void updateMember(Long memberNo, UpdateMemberRequest request) {
        Member member = memberRepository.findById(memberNo).orElseThrow(RuntimeException::new);
        member.update(request.getName(), request.getAge());

        MemberUpdateEventQueue memberUpdateEventQueue = MemberUpdateEventQueue.of(memberNo);
        memberUpdateEventQueueRepository.save(memberUpdateEventQueue);
    }

    @Transactional
    public void deleteMember(Long memberNo) {
        Member member = memberRepository.findById(memberNo).orElseThrow(RuntimeException::new);
        memberRepository.delete(member);
    }
}
