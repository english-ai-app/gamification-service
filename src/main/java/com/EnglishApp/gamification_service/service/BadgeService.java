package com.EnglishApp.gamification_service.service;

import com.EnglishApp.gamification_service.domain.dto.BadgeResponse;
import com.EnglishApp.gamification_service.domain.dto.UserBadgeResponse;

import java.util.List;

public interface BadgeService {
    List<BadgeResponse> getActiveBadges();

    List<UserBadgeResponse> getUserBadges(Long userId);
}
