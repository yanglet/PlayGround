package com.example.ranking.service;

import com.example.ranking.entity.Member;
import com.example.ranking.repository.MemberRepository;
import com.example.ranking.service.dto.member.MemberRequest;
import com.example.ranking.service.dto.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RankingService rankingService;

    @Transactional(readOnly = true)
    public List<MemberResponse> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemberResponse createMember(MemberRequest request) {
        Member member = Member.of(request.getMemberName(), request.getScore());
        memberRepository.save(member);
        rankingService.createRanking(member.getMemberName(), member.getScore());
        return MemberResponse.of(member);
    }
}
