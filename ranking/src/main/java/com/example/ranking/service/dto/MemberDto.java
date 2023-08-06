package com.example.ranking.service.dto;

import com.example.ranking.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    private final Long memberNo;
    private final String memberName;
    private final Long score;
    private final LocalDateTime insertDate;
    private final String insertOperator;
    private final LocalDateTime updateDate;
    private final String updateOperator;

    public MemberDto(Member member) {
        this.memberNo = member.getMemberNo();
        this.memberName = member.getMemberName();
        this.score = member.getScore();
        this.insertDate = member.getInsertDate();
        this.insertOperator = member.getInsertOperator();
        this.updateDate = member.getUpdateDate();
        this.updateOperator = member.getUpdateOperator();
    }
}
