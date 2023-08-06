package com.example.ranking.service;

import com.example.ranking.repository.MemberRepository;
import com.example.ranking.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDto> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
    }
}
