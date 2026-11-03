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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(name = "item_type", nullable = false, length = 32)
    private String itemType;

    @Column(name = "price_coin", nullable = false)
    private Integer priceCoin;

    @Column(name = "price_gem", nullable = false)
    private Integer priceGem;

    @Column(name = "icon_url", length = 512)
    private String iconUrl;

    @Column(name = "effect_json", columnDefinition = "json")
    private String effectJson;

    @Column(nullable = false)
    private Byte status;
}
