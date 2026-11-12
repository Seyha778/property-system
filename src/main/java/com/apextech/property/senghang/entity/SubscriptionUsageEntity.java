package com.apextech.property.senghang.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(
        name = "subscription_usage", schema ="property"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionUsageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_subscription_id", nullable = false)
    private Long userSubscriptionId;

    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart;

    @Column(name = "period_end", nullable = false)
    private LocalDate periodEnd;

    @Column(name = "posts_created", nullable = false)
    @Builder.Default
    private Integer postsCreated = 0;

    @Column(name = "created_on", updatable = false)
    private OffsetDateTime createdOn;

    @Column(name = "updated_on")
    private OffsetDateTime updatedOn;

    @PrePersist
    protected void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        if (createdOn == null) createdOn = now;
        if (updatedOn == null) updatedOn = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = OffsetDateTime.now();
    }
}
