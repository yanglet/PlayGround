package com.example.ranking.service.dto.ranking;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RankingResponse {
    private String memberName;
    private Long score;

    public static RankingResponse of(String memberName, Long score) {
        RankingResponse rankingResponse = new RankingResponse();
        rankingResponse.setMemberName(memberName);
        rankingResponse.setScore(score);
        return rankingResponse;
    }
}
