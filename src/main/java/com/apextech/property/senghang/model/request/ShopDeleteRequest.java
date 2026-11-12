package com.apextech.property.senghang.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDeleteRequest {

    @NotBlank(message = "Listing code is required")
    @Size(max = 50)
    @JsonProperty("listing_code")
    private String listingCode;

    @JsonProperty("seller_id")
    private String sellerId;

}
