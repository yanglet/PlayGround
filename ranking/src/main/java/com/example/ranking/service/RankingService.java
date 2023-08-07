package com.example.ranking.service;

import com.example.ranking.service.dto.ranking.RankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final RedisTemplate<String, String> redisTemplate;
    private final String REDIS_KEY = "ranking";
    
    public void createRanking(String name, Long score) {
        redisTemplate.opsForZSet().add(REDIS_KEY, name, score);
    }

    public List<RankingResponse> getRankings() {
        return Objects.requireNonNull(redisTemplate.opsForZSet().reverseRangeWithScores(REDIS_KEY, 0, 100000))
                .stream()
                .map(tuple -> new RankingResponse(tuple.getValue(), tuple.getScore().longValue()))
                .collect(Collectors.toList());
    }
}
