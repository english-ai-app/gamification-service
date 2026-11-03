package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.UserQuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserQuestRepository extends JpaRepository<UserQuest, Long> {
    List<UserQuest> findByUserIdAndAssignedDate(Long userId, LocalDate assignedDate);

    List<UserQuest> findByUserIdAndAssignedDateAndStatus(
            Long userId,
            LocalDate assignedDate,
            Byte status
    );

    Optional<UserQuest> findByUserIdAndQuestIdAndAssignedDate(
            Long userId,
            Long questId,
            LocalDate assignedDate
    );
}
