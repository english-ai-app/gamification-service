package com.EnglishApp.gamification_service.service;

import com.EnglishApp.gamification_service.domain.dto.InventoryItemResponse;
import com.EnglishApp.gamification_service.domain.dto.ItemResponse;
import com.EnglishApp.gamification_service.domain.dto.ItemTransactionResponse;

import java.util.List;

public interface StoreService {
    List<ItemResponse> getActiveItems(String itemType);

    List<InventoryItemResponse> getUserInventory(Long userId);

    List<ItemTransactionResponse> getUserTransactions(Long userId);
}
