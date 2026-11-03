package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.LeaderboardPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaderboardPeriodRepository extends JpaRepository<LeaderboardPeriod, Long> {
    List<LeaderboardPeriod> findByStatus(Byte status);

    List<LeaderboardPeriod> findByPeriodTypeAndStatus(String periodType, Byte status);
}
