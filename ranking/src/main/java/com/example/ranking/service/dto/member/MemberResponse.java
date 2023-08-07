package com.example.ranking.service.dto.member;

import com.example.ranking.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {
    private Long memberNo;
    private String memberName;
    private Long score;
    private LocalDateTime insertDate;
    private String insertOperator;
    private LocalDateTime updateDate;
    private String updateOperator;

    public static MemberResponse of(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberNo(member.getMemberNo());
        memberResponse.setMemberName(member.getMemberName());
        memberResponse.setScore(member.getScore());
        memberResponse.setInsertDate(member.getInsertDate());
        memberResponse.setInsertOperator(member.getInsertOperator());
        memberResponse.setUpdateDate(member.getUpdateDate());
        memberResponse.setUpdateOperator(member.getUpdateOperator());
        return memberResponse;
    }
}
