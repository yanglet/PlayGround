package com.example.ranking.service.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingResponse {
    private final String memberName;
    private final Long score;
}
