package com.apextech.property.senghang.model.response;

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
public class LocationResponse {

    private Long id;

    private String provinceId = "";

    private String districtId = "";

    private String communeId = "";

    private String villageId = "";

    private String provinceNameEn = "";

    private String provinceNameKh = "";

    private String districtNameEn = "";

    private String districtNameKh = "";

    private String communeNameEn = "";

    private String communeNameKh = "";

    private String villageNameEn = "";

    private String villageNameKh = "";

    private String road = "";

    private String homeNumber = "";
    private String nameEn = "";
    private String nameKm = "";
}
