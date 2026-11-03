package com.EnglishApp.gamification_service.service.impl;

import com.EnglishApp.gamification_service.common.Const;
import com.EnglishApp.gamification_service.domain.dto.BadgeResponse;
import com.EnglishApp.gamification_service.domain.dto.UserBadgeResponse;
import com.EnglishApp.gamification_service.domain.model.Badge;
import com.EnglishApp.gamification_service.domain.model.UserBadge;
import com.EnglishApp.gamification_service.repo.BadgeRepository;
import com.EnglishApp.gamification_service.repo.UserBadgeRepository;
import com.EnglishApp.gamification_service.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BadgeResponse> getActiveBadges() {
        return badgeRepository.findByStatus(Const.ACTIVE_STATUS.ACTIVE)
                .stream()
                .map(this::toBadgeResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBadgeResponse> getUserBadges(Long userId) {
        return userBadgeRepository.findByIdUserId(userId)
                .stream()
                .map(this::toUserBadgeResponse)
                .toList();
    }

    private BadgeResponse toBadgeResponse(Badge badge) {
        return BadgeResponse.builder()
                .id(badge.getId())
                .name(badge.getName())
                .description(badge.getDescription())
                .iconUrl(badge.getIconUrl())
                .conditionJson(badge.getConditionJson())
                .build();
    }

    private UserBadgeResponse toUserBadgeResponse(UserBadge userBadge) {
        Badge badge = userBadge.getBadge();
        return UserBadgeResponse.builder()
                .badgeId(badge.getId())
                .name(badge.getName())
                .description(badge.getDescription())
                .iconUrl(badge.getIconUrl())
                .earnedAt(userBadge.getEarnedAt())
                .build();
    }
}
