package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(Byte status);

    List<Item> findByItemTypeAndStatus(String itemType, Byte status);
}
