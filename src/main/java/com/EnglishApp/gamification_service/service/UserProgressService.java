package com.EnglishApp.gamification_service.service;

import com.EnglishApp.gamification_service.domain.dto.UserProgressResponse;

public interface UserProgressService {
    UserProgressResponse getOrCreateProgress(Long userId);
}
