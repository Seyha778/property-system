package com.apextech.property.senghang.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "roles", schema ="property")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_code", nullable = false, unique = true, length = 30)
    private String roleCode;

    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;

    @Column(name = "created_on", updatable = false)
    private OffsetDateTime createdOn;

    @PrePersist
    protected void onCreate() {
        if (createdOn == null) {
            createdOn = OffsetDateTime.now();
        }
    }
}