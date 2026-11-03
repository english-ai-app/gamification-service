package com.EnglishApp.gamification_service.service;

import com.EnglishApp.gamification_service.domain.dto.QuestResponse;
import com.EnglishApp.gamification_service.domain.dto.UserQuestResponse;

import java.time.LocalDate;
import java.util.List;

public interface QuestService {
    List<QuestResponse> getActiveQuests(String questType);

    List<UserQuestResponse> getUserQuests(Long userId, LocalDate assignedDate);
}
