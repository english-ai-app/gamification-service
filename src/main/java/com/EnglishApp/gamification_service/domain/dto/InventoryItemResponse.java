package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InventoryItemResponse {
    private Long inventoryId;
    private Long itemId;
    private String name;
    private String description;
    private String itemType;
    private String iconUrl;
    private Integer quantity;
    private LocalDateTime acquiredAt;
}
