package com.example.service.dto.response;

import com.example.repository.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadMemberResponse {

    private final Long memberNo;
    private final String name;
    private final Integer age;

    public static ReadMemberResponse from(Member member) {
        return new ReadMemberResponse(member.getMemberNo(), member.getName(), member.getAge());
    }
}
