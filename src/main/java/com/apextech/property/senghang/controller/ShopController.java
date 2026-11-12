package com.apextech.property.senghang.controller;

import com.apextech.property.senghang.common.ResponseMessage;
import com.apextech.property.senghang.common.ResponseMessageBuilder;
import com.apextech.property.senghang.model.request.ShopCreateRequest;
import com.apextech.property.senghang.model.request.ShopDeleteRequest;
import com.apextech.property.senghang.model.request.ShopListRequest;
import com.apextech.property.senghang.model.request.ShopUpdateRequest;
import com.apextech.property.senghang.model.response.ListResponse;
import com.apextech.property.senghang.model.response.ProductResponse;
import com.apextech.property.senghang.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/senghang")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/create")
    public ResponseMessage<ProductResponse> create(@Valid @RequestBody ShopCreateRequest request) {
        ProductResponse response = shopService.create(request);
        return new ResponseMessageBuilder<ProductResponse>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/update")
    public ResponseMessage<ProductResponse> update(@Valid @RequestBody ShopUpdateRequest request) {
        ProductResponse response = shopService.update(request);
        return new ResponseMessageBuilder<ProductResponse>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/delete")
    public ResponseMessage<?> delete(@Valid @RequestBody ShopDeleteRequest request) {
        shopService.delete(request);
        return new ResponseMessageBuilder<ProductResponse>()
                .success()
                .data(null)
                .build();
    }

    @PostMapping("/list")
    public ResponseMessage<ListResponse<ProductResponse>> list(@Valid @RequestBody ShopListRequest request) {
        ListResponse<ProductResponse> response = shopService.list(request);
        return new ResponseMessageBuilder<ListResponse<ProductResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/listPost")
    public ResponseMessage<ListResponse<ProductResponse>> listPostByUser(@Valid @RequestBody ShopListRequest request) {
        ListResponse<ProductResponse> response = shopService.listPost(request);
        return new ResponseMessageBuilder<ListResponse<ProductResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/getFavorite")
    public ResponseMessage<ListResponse<ProductResponse>> getFavorite(@Valid @RequestBody ShopListRequest request) {
        ListResponse<ProductResponse> response = shopService.listFavorite(request);
        return new ResponseMessageBuilder<ListResponse<ProductResponse>>()
                .success()
                .data(response)
                .build();
    }

}
