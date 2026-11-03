package com.EnglishApp.gamification_service.controller;

import com.EnglishApp.gamification_service.common.GamificationUserContext;
import com.EnglishApp.gamification_service.domain.dto.BadgeResponse;
import com.EnglishApp.gamification_service.domain.dto.UserBadgeResponse;
import com.EnglishApp.gamification_service.service.BadgeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/gamification/badges", produces = MediaType.APPLICATION_JSON_VALUE)
public class BadgeController {
    private final GamificationUserContext userContext;
    private final BadgeService badgeService;

    @GetMapping
    public List<BadgeResponse> getBadges() {
        return badgeService.getActiveBadges();
    }

    @GetMapping("/mine")
    public List<UserBadgeResponse> getMyBadges(HttpServletRequest request) {
        Long userId = userContext.getAuthenticatedUserId(request);
        return badgeService.getUserBadges(userId);
    }
}
