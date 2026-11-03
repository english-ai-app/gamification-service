package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QuestResponse {
    private Long id;
    private String title;
    private String description;
    private String questType;
    private String requirementJson;
    private Integer xpReward;
    private Integer coinReward;
    private Byte status;
    private LocalDateTime createdAt;
}
