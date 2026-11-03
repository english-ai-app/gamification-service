package com.EnglishApp.gamification_service.controller;

import com.EnglishApp.gamification_service.common.GamificationUserContext;
import com.EnglishApp.gamification_service.domain.dto.UserProgressResponse;
import com.EnglishApp.gamification_service.service.UserProgressService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/gamification/progress", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserProgressController {
    private final GamificationUserContext userContext;
    private final UserProgressService userProgressService;

    @GetMapping
    public UserProgressResponse getProgress(HttpServletRequest request) {
        Long userId = userContext.getAuthenticatedUserId(request);
        return userProgressService.getOrCreateProgress(userId);
    }
}
