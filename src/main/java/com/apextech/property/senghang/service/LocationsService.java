package com.apextech.property.senghang.service;

import com.apextech.property.senghang.common.ResponseMessageBuilder;
import com.apextech.property.senghang.model.response.LocationResponse;

import java.util.List;

public interface LocationsService {

    List<LocationResponse> getProvinces();

    List<LocationResponse> getDistricts(String provinceId);

    List<LocationResponse> getCommunes(String districtId);

    List<LocationResponse> getVillages(String communeId);

}
