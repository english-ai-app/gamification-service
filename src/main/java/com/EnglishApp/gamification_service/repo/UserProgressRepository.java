package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
}
