package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserQuestResponse {
    private Long id;
    private Long questId;
    private String title;
    private String description;
    private String questType;
    private Integer progress;
    private Byte status;
    private LocalDate assignedDate;
    private LocalDateTime completedAt;
    private Integer xpReward;
    private Integer coinReward;
}
