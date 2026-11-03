package com.EnglishApp.gamification_service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class GamificationUserContext {
    private static final String HEADER_ACTION_USER = "X-Action-User";
    private static final String HEADER_USER_ID = "X-User-Id";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Long getAuthenticatedUserId(HttpServletRequest request) {
        Long userId = getUserIdFromActionUser(request.getHeader(HEADER_ACTION_USER));
        if (isValidUserId(userId)) {
            return userId;
        }

        userId = parseUserId(request.getHeader(HEADER_USER_ID));
        if (isValidUserId(userId)) {
            return userId;
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
    }

    private Long getUserIdFromActionUser(String actionUserHeader) {
        if (actionUserHeader == null || actionUserHeader.isBlank()) {
            return null;
        }
        try {
            JsonNode actionUser = objectMapper.readTree(actionUserHeader);
            JsonNode userId = actionUser.hasNonNull("id")
                    ? actionUser.get("id")
                    : actionUser.get("userId");
            return userId == null || !userId.canConvertToLong() ? null : userId.asLong();
        } catch (JsonProcessingException exception) {
            return null;
        }
    }

    private Long parseUserId(String userIdHeader) {
        if (userIdHeader == null || userIdHeader.isBlank()) {
            return null;
        }
        try {
            return Long.parseLong(userIdHeader.trim());
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    private boolean isValidUserId(Long userId) {
        return userId != null && userId > 0;
    }
}
