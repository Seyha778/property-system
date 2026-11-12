package com.apextech.property.senghang.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ShopCreateRequest {

    @NotBlank(message = "title name is required")
    @JsonProperty("title")
    private String title;

    @JsonProperty("property_type")
    private String propertyType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @NotNull(message = "Sale price is required")
    @DecimalMin(value = "0.01", message = "Sale Price must be greater than zero")
    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @DecimalMin(value = "0.00", message = "Rental price cannot be negative")
    @JsonProperty("rental_price")
    private BigDecimal rentalPrice;

    @DecimalMin(value = "0.00", message = "Compare price cannot be negative")
    @JsonProperty("compare_price")
    private BigDecimal comparePrice;

    @NotBlank(message = "Currency is required")
    @JsonProperty("currency")
    private String currency;

    @NotBlank(message = "Status is required")
    @JsonProperty("status")
    private String status;

    @Column(name = "province_id", nullable = false)
    private String provinceId;

    @Column(name = "district_id", nullable = false)
    private String districtId;

    @Column(name = "commune_id", nullable = false)
    private String communeId;

    @Column(name = "village_id")
    private String villageId;

    @Column(name = "road")
    private String road;

    @JsonProperty("is_featured")
    private Boolean isFeatured;
}
