package com.EnglishApp.gamification_service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemTransactionResponse {
    private Long id;
    private Long itemId;
    private String itemName;
    private Integer quantity;
    private Integer pricePaidCoin;
    private Integer pricePaidGem;
    private String transactionType;
    private LocalDateTime createdAt;
}
