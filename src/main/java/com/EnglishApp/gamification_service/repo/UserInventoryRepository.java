package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.UserInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInventoryRepository extends JpaRepository<UserInventory, Long> {
    List<UserInventory> findByUserId(Long userId);

    Optional<UserInventory> findByUserIdAndItemId(Long userId, Long itemId);
}
