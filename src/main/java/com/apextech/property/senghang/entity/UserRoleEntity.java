package com.apextech.property.senghang.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "user_roles", schema ="property"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "created_on", updatable = false)
    private OffsetDateTime createdOn;

    @PrePersist
    protected void onCreate() {
        if (createdOn == null) {
            createdOn = OffsetDateTime.now();
        }
    }
}