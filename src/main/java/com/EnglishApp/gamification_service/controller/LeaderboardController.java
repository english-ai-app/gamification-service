package com.EnglishApp.gamification_service.controller;

import com.EnglishApp.gamification_service.domain.dto.LeaderboardEntryResponse;
import com.EnglishApp.gamification_service.domain.dto.LeaderboardPeriodResponse;
import com.EnglishApp.gamification_service.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/gamification/leaderboards", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    @GetMapping
    public List<LeaderboardPeriodResponse> getActivePeriods(
            @RequestParam(required = false) String type
    ) {
        return leaderboardService.getActivePeriods(type);
    }

    @GetMapping("/{periodId}/entries")
    public List<LeaderboardEntryResponse> getEntries(@PathVariable Long periodId) {
        return leaderboardService.getEntries(periodId);
    }
}
