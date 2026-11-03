package com.EnglishApp.gamification_service.service.impl;

import com.EnglishApp.gamification_service.common.Const;
import com.EnglishApp.gamification_service.domain.dto.LeaderboardEntryResponse;
import com.EnglishApp.gamification_service.domain.dto.LeaderboardPeriodResponse;
import com.EnglishApp.gamification_service.domain.model.LeaderboardEntry;
import com.EnglishApp.gamification_service.domain.model.LeaderboardPeriod;
import com.EnglishApp.gamification_service.exception.BusinessException;
import com.EnglishApp.gamification_service.repo.LeaderboardEntryRepository;
import com.EnglishApp.gamification_service.repo.LeaderboardPeriodRepository;
import com.EnglishApp.gamification_service.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {
    private final LeaderboardPeriodRepository leaderboardPeriodRepository;
    private final LeaderboardEntryRepository leaderboardEntryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LeaderboardPeriodResponse> getActivePeriods(String periodType) {
        List<LeaderboardPeriod> periods = periodType == null || periodType.isBlank()
                ? leaderboardPeriodRepository.findByStatus(Const.LEADERBOARD_PERIOD_STATUS.ACTIVE)
                : leaderboardPeriodRepository.findByPeriodTypeAndStatus(
                        periodType.trim().toUpperCase(),
                        Const.LEADERBOARD_PERIOD_STATUS.ACTIVE
                );
        return periods.stream().map(this::toPeriodResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeaderboardEntryResponse> getEntries(Long periodId) {
        if (!leaderboardPeriodRepository.existsById(periodId)) {
            throw new BusinessException(
                    "LEADERBOARD_PERIOD_NOT_FOUND",
                    "Leaderboard period was not found",
                    HttpStatus.NOT_FOUND
            );
        }
        return leaderboardEntryRepository.findByLeaderboardPeriodIdOrderByScoreDescIdAsc(periodId)
                .stream()
                .map(this::toEntryResponse)
                .toList();
    }

    private LeaderboardPeriodResponse toPeriodResponse(LeaderboardPeriod period) {
        return LeaderboardPeriodResponse.builder()
                .id(period.getId())
                .periodType(period.getPeriodType())
                .name(period.getName())
                .periodStart(period.getPeriodStart())
                .periodEnd(period.getPeriodEnd())
                .status(period.getStatus())
                .build();
    }

    private LeaderboardEntryResponse toEntryResponse(LeaderboardEntry entry) {
        return LeaderboardEntryResponse.builder()
                .userId(entry.getUserId())
                .score(entry.getScore())
                .rankPosition(entry.getRankPosition())
                .updatedAt(entry.getUpdatedAt())
                .build();
    }
}
