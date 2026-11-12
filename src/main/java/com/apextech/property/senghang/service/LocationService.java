package com.apextech.property.senghang.service;

import com.apextech.property.senghang.model.request.LocationRequest;
import com.apextech.property.senghang.model.request.ShopCreateRequest;
import com.apextech.property.senghang.model.request.ShopDeleteRequest;
import com.apextech.property.senghang.model.request.ShopListRequest;
import com.apextech.property.senghang.model.request.ShopUpdateRequest;
import com.apextech.property.senghang.model.response.ListResponse;
import com.apextech.property.senghang.model.response.LocationResponse;
import com.apextech.property.senghang.model.response.ProductResponse;

public interface LocationService {
    LocationResponse getAddress(LocationRequest request);
    ListResponse<LocationResponse> getProvince(LocationRequest request);
    ListResponse<LocationResponse> getDistrict(LocationRequest request);
    ListResponse<LocationResponse> getCommune(LocationRequest request);
    ListResponse<LocationResponse> getVillage(LocationRequest request);

}
