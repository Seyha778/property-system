package com.apextech.property.senghang.service;

import com.apextech.property.senghang.model.request.ShopCreateRequest;
import com.apextech.property.senghang.model.request.ShopDeleteRequest;
import com.apextech.property.senghang.model.request.ShopListRequest;
import com.apextech.property.senghang.model.request.ShopUpdateRequest;
import com.apextech.property.senghang.model.response.ListResponse;
import com.apextech.property.senghang.model.response.ProductResponse;

public interface ShopService {

    ProductResponse create(ShopCreateRequest request);

    ProductResponse update(ShopUpdateRequest request);

    void delete(ShopDeleteRequest request);

    ListResponse<ProductResponse> list(ShopListRequest request);

    ListResponse<ProductResponse> listPost(ShopListRequest request);

    ListResponse<ProductResponse> listFavorite(ShopListRequest request);
}
