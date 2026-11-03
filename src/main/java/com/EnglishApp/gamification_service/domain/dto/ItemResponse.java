package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private String itemType;
    private Integer priceCoin;
    private Integer priceGem;
    private String iconUrl;
    private String effectJson;
}
