package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserBadgeResponse {
    private Long badgeId;
    private String name;
    private String description;
    private String iconUrl;
    private LocalDateTime earnedAt;
}
