package com.apextech.property.senghang.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopUpdateRequest {

    @JsonProperty("product_code")
    private String listingCode;

    @JsonProperty("title")
    private String title;

    @JsonProperty("property_type")
    private String propertyType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @JsonProperty("rent_price")
    private BigDecimal rentPrice;

    @JsonProperty("compare_price")
    private BigDecimal comparePrice;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("status")
    private String status;

    @JsonProperty("is_featured")
    private Boolean isFeatured;
}
