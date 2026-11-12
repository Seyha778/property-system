package com.apextech.property.senghang.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;

    private String listingCode;

    private String title;

    private String propertyType;

    private String propertyName;

    private String description;

    private BigDecimal salePrice;

    private BigDecimal rentPrice;

    private BigDecimal comparePrice;

    private String currency;

    private String status;

    private Boolean isFeatured;

    private OffsetDateTime createdOn;

    private OffsetDateTime modifiedOn;
}
