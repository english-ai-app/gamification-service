package com.EnglishApp.gamification_service.service.impl;

import com.EnglishApp.gamification_service.common.Const;
import com.EnglishApp.gamification_service.domain.dto.InventoryItemResponse;
import com.EnglishApp.gamification_service.domain.dto.ItemResponse;
import com.EnglishApp.gamification_service.domain.dto.ItemTransactionResponse;
import com.EnglishApp.gamification_service.domain.model.Item;
import com.EnglishApp.gamification_service.domain.model.ItemTransaction;
import com.EnglishApp.gamification_service.domain.model.UserInventory;
import com.EnglishApp.gamification_service.repo.ItemRepository;
import com.EnglishApp.gamification_service.repo.ItemTransactionRepository;
import com.EnglishApp.gamification_service.repo.UserInventoryRepository;
import com.EnglishApp.gamification_service.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final ItemRepository itemRepository;
    private final UserInventoryRepository userInventoryRepository;
    private final ItemTransactionRepository itemTransactionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ItemResponse> getActiveItems(String itemType) {
        List<Item> items = itemType == null || itemType.isBlank()
                ? itemRepository.findByStatus(Const.ACTIVE_STATUS.ACTIVE)
                : itemRepository.findByItemTypeAndStatus(
                        itemType.trim().toUpperCase(),
                        Const.ACTIVE_STATUS.ACTIVE
                );
        return items.stream().map(this::toItemResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryItemResponse> getUserInventory(Long userId) {
        return userInventoryRepository.findByUserId(userId)
                .stream()
                .map(this::toInventoryItemResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemTransactionResponse> getUserTransactions(Long userId) {
        return itemTransactionRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::toTransactionResponse)
                .toList();
    }

    private ItemResponse toItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .itemType(item.getItemType())
                .priceCoin(item.getPriceCoin())
                .priceGem(item.getPriceGem())
                .iconUrl(item.getIconUrl())
                .effectJson(item.getEffectJson())
                .build();
    }

    private InventoryItemResponse toInventoryItemResponse(UserInventory inventory) {
        Item item = inventory.getItem();
        return InventoryItemResponse.builder()
                .inventoryId(inventory.getId())
                .itemId(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .itemType(item.getItemType())
                .iconUrl(item.getIconUrl())
                .quantity(inventory.getQuantity())
                .acquiredAt(inventory.getAcquiredAt())
                .build();
    }

    private ItemTransactionResponse toTransactionResponse(ItemTransaction transaction) {
        Item item = transaction.getItem();
        return ItemTransactionResponse.builder()
                .id(transaction.getId())
                .itemId(item.getId())
                .itemName(item.getName())
                .quantity(transaction.getQuantity())
                .pricePaidCoin(transaction.getPricePaidCoin())
                .pricePaidGem(transaction.getPricePaidGem())
                .transactionType(transaction.getTransactionType())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
