package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.LeaderboardEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntry, Long> {
    List<LeaderboardEntry> findByLeaderboardPeriodIdOrderByScoreDescIdAsc(Long periodId);

    Optional<LeaderboardEntry> findByLeaderboardPeriodIdAndUserId(Long periodId, Long userId);
}
