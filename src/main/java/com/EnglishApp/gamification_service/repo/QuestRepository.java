package com.EnglishApp.gamification_service.repo;

import com.EnglishApp.gamification_service.domain.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    List<Quest> findByStatus(Byte status);

    List<Quest> findByQuestTypeAndStatus(String questType, Byte status);
}
