package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LeaderboardEntryResponse {
    private Long userId;
    private Long score;
    private Integer rankPosition;
    private LocalDateTime updatedAt;
}
