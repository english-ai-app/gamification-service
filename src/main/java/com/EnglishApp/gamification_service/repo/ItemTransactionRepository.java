package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTransactionRepository extends JpaRepository<ItemTransaction, Long> {
    List<ItemTransaction> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<ItemTransaction> findByUserIdAndItemIdOrderByCreatedAtDesc(Long userId, Long itemId);
}
