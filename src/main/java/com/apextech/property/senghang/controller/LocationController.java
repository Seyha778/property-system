package com.apextech.property.senghang.controller;

import com.apextech.property.senghang.common.ResponseMessage;
import com.apextech.property.senghang.common.ResponseMessageBuilder;
import com.apextech.property.senghang.model.request.LocationRequest;
import com.apextech.property.senghang.model.response.ListResponse;
import com.apextech.property.senghang.model.response.LocationResponse;
import com.apextech.property.senghang.service.LocationService;
import com.apextech.property.senghang.service.LocationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationsService locationsService;

    @PostMapping("/getAddress")
    public ResponseMessage<LocationResponse> getAddress(@Valid @RequestBody LocationRequest request) {
        LocationResponse response = locationService.getAddress(request);
        return new ResponseMessageBuilder<LocationResponse>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/getProvince")
    public ResponseMessage<ListResponse<LocationResponse>> getProvince(@Valid @RequestBody LocationRequest request) {
        ListResponse<LocationResponse> response = locationService.getProvince(request);
        return new ResponseMessageBuilder<ListResponse<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/getDistrict")
    public ResponseMessage<ListResponse<LocationResponse>> getDistrict(@Valid @RequestBody LocationRequest request) {
        ListResponse<LocationResponse> response = locationService.getDistrict(request);
        return new ResponseMessageBuilder<ListResponse<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/getCommune")
    public ResponseMessage<ListResponse<LocationResponse>> getCommune(@Valid @RequestBody LocationRequest request) {
        ListResponse<LocationResponse> response = locationService.getCommune(request);
        return new ResponseMessageBuilder<ListResponse<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/getVillage")
    public ResponseMessage<ListResponse<LocationResponse>> getVillage(@Valid @RequestBody LocationRequest request) {
        ListResponse<LocationResponse> response = locationService.getVillage(request);
        return new ResponseMessageBuilder<ListResponse<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/provinces")
    public ResponseMessage<List<LocationResponse>> province(@Valid @RequestBody LocationRequest request) {
        List<LocationResponse> response = locationsService.getProvinces();
        return new ResponseMessageBuilder<List<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }
    @PostMapping("/districts")
    public ResponseMessage<List<LocationResponse>> districts(@Valid @RequestBody LocationRequest request) {
        List<LocationResponse> response = locationsService.getDistricts(request.getProvinceId());
        return new ResponseMessageBuilder<List<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/communes")
    public ResponseMessage<List<LocationResponse>> communes(@Valid @RequestBody LocationRequest request) {
        List<LocationResponse> response = locationsService.getCommunes(request.getDistrictId());
        return new ResponseMessageBuilder<List<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }

    @PostMapping("/villages")
    public ResponseMessage<List<LocationResponse>> villages(@Valid @RequestBody LocationRequest request) {
        List<LocationResponse> response = locationsService.getVillages(request.getCommuneId());
        return new ResponseMessageBuilder<List<LocationResponse>>()
                .success()
                .data(response)
                .build();
    }
}
