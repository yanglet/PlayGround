package com.example.springevent.service;

import com.example.springevent.event.MemberCreatedEvent;
import com.example.springevent.repository.Member;
import com.example.springevent.repository.MemberRepository;
import com.example.springevent.service.dto.ReadMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public List<ReadMemberResponse> readMembers() {
        return memberRepository.findAll()
                .stream()
                .map(member -> ReadMemberResponse.of(member.getMemberNo(), member.getMemberName(), member.getAge()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createMember(String memberName, Long age) {
        Member member = Member.of(memberName, age);
        memberRepository.save(member);
        eventPublisher.publishEvent(MemberCreatedEvent.of(member.getMemberNo()));
        return member.getMemberNo();
    }
}
