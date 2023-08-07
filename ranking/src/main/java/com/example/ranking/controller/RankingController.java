package com.example.ranking.controller;

import com.example.ranking.service.RankingService;
import com.example.ranking.service.dto.ranking.RankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rankings")
public class RankingController {
    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<List<RankingResponse>> getRankings() {
        return ResponseEntity.ok(rankingService.getRankings());
    }
}
