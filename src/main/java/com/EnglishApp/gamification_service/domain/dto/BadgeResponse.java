package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeResponse {
    private Long id;
    private String name;
    private String description;
    private String iconUrl;
    private String conditionJson;
}
