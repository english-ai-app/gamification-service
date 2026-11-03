package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LeaderboardPeriodResponse {
    private Long id;
    private String periodType;
    private String name;
    private LocalDateTime periodStart;
    private LocalDateTime periodEnd;
    private Byte status;
}
