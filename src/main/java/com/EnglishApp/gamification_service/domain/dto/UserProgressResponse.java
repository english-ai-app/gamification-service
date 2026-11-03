package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserProgressResponse {
    private Long userId;
    private Long xp;
    private Integer level;
    private Integer coins;
    private Integer gems;
    private Integer currentStreak;
    private Integer longestStreak;
    private LocalDate lastActiveDate;
    private LocalDateTime updatedAt;
}
