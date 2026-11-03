package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<Badge> findByStatus(Byte status);
}
