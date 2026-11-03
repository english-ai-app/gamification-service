package com.EnglishApp.gamification_service.controller;

import com.EnglishApp.gamification_service.common.GamificationUserContext;
import com.EnglishApp.gamification_service.domain.dto.QuestResponse;
import com.EnglishApp.gamification_service.domain.dto.UserQuestResponse;
import com.EnglishApp.gamification_service.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/gamification/quests", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestController {
    private final GamificationUserContext userContext;
    private final QuestService questService;

    @GetMapping
    public List<QuestResponse> getActiveQuests(
            @RequestParam(required = false) String type
    ) {
        return questService.getActiveQuests(type);
    }

    @GetMapping("/mine")
    public List<UserQuestResponse> getMyQuests(
            HttpServletRequest request,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate assignedDate
    ) {
        Long userId = userContext.getAuthenticatedUserId(request);
        return questService.getUserQuests(userId, assignedDate);
    }
}
