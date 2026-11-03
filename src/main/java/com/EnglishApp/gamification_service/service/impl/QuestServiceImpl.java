package com.EnglishApp.gamification_service.service.impl;

import com.EnglishApp.gamification_service.common.Const;
import com.EnglishApp.gamification_service.domain.dto.QuestResponse;
import com.EnglishApp.gamification_service.domain.dto.UserQuestResponse;
import com.EnglishApp.gamification_service.domain.model.Quest;
import com.EnglishApp.gamification_service.domain.model.UserQuest;
import com.EnglishApp.gamification_service.repo.QuestRepository;
import com.EnglishApp.gamification_service.repo.UserQuestRepository;
import com.EnglishApp.gamification_service.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {
    private final QuestRepository questRepository;
    private final UserQuestRepository userQuestRepository;

    @Override
    @Transactional(readOnly = true)
    public List<QuestResponse> getActiveQuests(String questType) {
        List<Quest> quests = questType == null || questType.isBlank()
                ? questRepository.findByStatus(Const.ACTIVE_STATUS.ACTIVE)
                : questRepository.findByQuestTypeAndStatus(
                        questType.trim().toUpperCase(),
                        Const.ACTIVE_STATUS.ACTIVE
                );
        return quests.stream().map(this::toQuestResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserQuestResponse> getUserQuests(Long userId, LocalDate assignedDate) {
        LocalDate date = assignedDate == null
                ? LocalDate.now(Const.GAMIFICATION_TIME_ZONE)
                : assignedDate;
        return userQuestRepository.findByUserIdAndAssignedDate(userId, date)
                .stream()
                .map(this::toUserQuestResponse)
                .toList();
    }

    private QuestResponse toQuestResponse(Quest quest) {
        return QuestResponse.builder()
                .id(quest.getId())
                .title(quest.getTitle())
                .description(quest.getDescription())
                .questType(quest.getQuestType())
                .requirementJson(quest.getRequirementJson())
                .xpReward(quest.getXpReward())
                .coinReward(quest.getCoinReward())
                .status(quest.getStatus())
                .createdAt(quest.getCreatedAt())
                .build();
    }

    private UserQuestResponse toUserQuestResponse(UserQuest userQuest) {
        Quest quest = userQuest.getQuest();
        return UserQuestResponse.builder()
                .id(userQuest.getId())
                .questId(quest.getId())
                .title(quest.getTitle())
                .description(quest.getDescription())
                .questType(quest.getQuestType())
                .progress(userQuest.getProgress())
                .status(userQuest.getStatus())
                .assignedDate(userQuest.getAssignedDate())
                .completedAt(userQuest.getCompletedAt())
                .xpReward(quest.getXpReward())
                .coinReward(quest.getCoinReward())
                .build();
    }
}
