package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.UserBadge;
import com.EnglishApp.gamification_service.domain.model.UserBadgeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge, UserBadgeId> {
    List<UserBadge> findByIdUserId(Long userId);

    boolean existsByIdUserIdAndIdBadgeId(Long userId, Long badgeId);
}
