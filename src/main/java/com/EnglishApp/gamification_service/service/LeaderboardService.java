package com.EnglishApp.gamification_service.service;

import com.EnglishApp.gamification_service.domain.dto.LeaderboardEntryResponse;
import com.EnglishApp.gamification_service.domain.dto.LeaderboardPeriodResponse;

import java.util.List;

public interface LeaderboardService {
    List<LeaderboardPeriodResponse> getActivePeriods(String periodType);

    List<LeaderboardEntryResponse> getEntries(Long periodId);
}
