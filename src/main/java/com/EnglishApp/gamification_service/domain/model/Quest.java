package com.EnglishApp.gamification_service.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quests")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 512)
    private String description;

    @Column(name = "quest_type", nullable = false, length = 32)
    private String questType;

    @Column(name = "requirement_json", nullable = false, columnDefinition = "json")
    private String requirementJson;

    @Column(name = "xp_reward", nullable = false)
    private Integer xpReward;

    @Column(name = "coin_reward", nullable = false)
    private Integer coinReward;

    @Column(nullable = false)
    private Byte status;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
