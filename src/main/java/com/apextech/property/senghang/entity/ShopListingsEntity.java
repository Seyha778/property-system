package com.apextech.property.senghang.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "shop_listings", schema = "property")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopListingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "listing_code", nullable = false)
    private String listingCode;

    @Column(name = "seller_id", nullable = false)
    private String sellerId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "property_type", nullable = false)
    private String propertyType;

    @Column(name = "sale_price", nullable = false, precision = 19, scale = 4)
    private BigDecimal salePrice;

    @Column(name = "compare_price", nullable = false, precision = 19, scale = 4)
    private BigDecimal comparePrice;

    @Column(name = "rental_price", nullable = false, precision = 19, scale = 4)
    private BigDecimal rentalPrice;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Builder.Default
    @Column(name = "status", nullable = false)
    private String status = "PA";

    @Builder.Default
    @Column(name = "is_featured", nullable = false)
    private boolean isFeatured = false;

    @Column(name = "view_count")
    private int viewCount;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "published_at", nullable = false, updatable = false)
    private OffsetDateTime publishedAt;

    @Column(name = "expired_at", nullable = false, updatable = false)
    private OffsetDateTime expiredAt;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    private OffsetDateTime createdOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_on")
    private OffsetDateTime modifiedOn;

}