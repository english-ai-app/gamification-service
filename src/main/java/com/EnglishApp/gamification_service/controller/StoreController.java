package com.EnglishApp.gamification_service.controller;

import com.EnglishApp.gamification_service.common.GamificationUserContext;
import com.EnglishApp.gamification_service.domain.dto.InventoryItemResponse;
import com.EnglishApp.gamification_service.domain.dto.ItemResponse;
import com.EnglishApp.gamification_service.domain.dto.ItemTransactionResponse;
import com.EnglishApp.gamification_service.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/gamification", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoreController {
    private final GamificationUserContext userContext;
    private final StoreService storeService;

    @GetMapping("/items")
    public List<ItemResponse> getItems(
            @RequestParam(required = false) String type
    ) {
        return storeService.getActiveItems(type);
    }

    @GetMapping("/inventory")
    public List<InventoryItemResponse> getMyInventory(HttpServletRequest request) {
        Long userId = userContext.getAuthenticatedUserId(request);
        return storeService.getUserInventory(userId);
    }

    @GetMapping("/transactions")
    public List<ItemTransactionResponse> getMyTransactions(HttpServletRequest request) {
        Long userId = userContext.getAuthenticatedUserId(request);
        return storeService.getUserTransactions(userId);
    }
}
