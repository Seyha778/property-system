package com.apextech.property.senghang.model.request;

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
public class ShopListRequest {
    private String keyword;

    private String propertyType;

    private String status;

    private String sellerId;

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 10;

}
