package com.EnglishApp.gamification_service.service.impl;

import com.EnglishApp.gamification_service.domain.dto.UserProgressResponse;
import com.EnglishApp.gamification_service.domain.model.UserProgress;
import com.EnglishApp.gamification_service.repo.UserProgressRepository;
import com.EnglishApp.gamification_service.service.UserProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProgressServiceImpl implements UserProgressService {
    private final UserProgressRepository userProgressRepository;

    @Override
    @Transactional
    public UserProgressResponse getOrCreateProgress(Long userId) {
        UserProgress progress = userProgressRepository.findById(userId)
                .orElseGet(() -> userProgressRepository.save(UserProgress.builder()
                        .userId(userId)
                        .xp(0L)
                        .level(1)
                        .coins(0)
                        .gems(0)
                        .currentStreak(0)
                        .longestStreak(0)
                        .build()));
        return toResponse(progress);
    }

    private UserProgressResponse toResponse(UserProgress progress) {
        return UserProgressResponse.builder()
                .userId(progress.getUserId())
                .xp(progress.getXp())
                .level(progress.getLevel())
                .coins(progress.getCoins())
                .gems(progress.getGems())
                .currentStreak(progress.getCurrentStreak())
                .longestStreak(progress.getLongestStreak())
                .lastActiveDate(progress.getLastActiveDate())
                .updatedAt(progress.getUpdatedAt())
                .build();
    }
}
