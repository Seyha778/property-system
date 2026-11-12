package com.apextech.property.senghang.service.impl;

import com.apextech.property.senghang.entity.CommunesEntity;
import com.apextech.property.senghang.entity.DistrictsEntity;
import com.apextech.property.senghang.entity.ProvincesEntity;
import com.apextech.property.senghang.entity.VillagesEntity;
import com.apextech.property.senghang.model.request.LocationRequest;
import com.apextech.property.senghang.model.response.ListResponse;
import com.apextech.property.senghang.model.response.LocationResponse;
import com.apextech.property.senghang.repository.CommunesRepository;
import com.apextech.property.senghang.repository.DistrictsRepository;
import com.apextech.property.senghang.repository.ProvincesRepository;
import com.apextech.property.senghang.repository.ShopAddressesRepository;
import com.apextech.property.senghang.repository.VillageRepository;
import com.apextech.property.senghang.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final ShopAddressesRepository shopAddressesRepository;
    private final ProvincesRepository provincesRepository;
    private final DistrictsRepository districtsRepository;
    private final CommunesRepository communesRepository;
    private final VillageRepository villageRepository;

    @Override
    public LocationResponse getAddress(LocationRequest request) {

        if (!StringUtils.hasText(request.getListingCode())) {
            return new LocationResponse();
        }
        return shopAddressesRepository.findAddressByListingCode(request.getListingCode());

    }

    @Override
    public ListResponse<LocationResponse> getProvince(LocationRequest request) {

        Pageable pageable = PageRequest.of(0, 25);
        Page<ProvincesEntity> list = provincesRepository.findAllByOrderByIdDesc(pageable);

        log.info("Total province data size:{}", list.getSize());

        List<LocationResponse> content = list.getContent()
                .stream()
                .map(this::toProvinceResponse)
                .toList();

        return ListResponse.<LocationResponse>builder()
                .content(content)
                .totalElements(list.getTotalElements())
                .totalPages(list.getTotalPages())
                .build();

    }

    @Override
    public ListResponse<LocationResponse> getDistrict(LocationRequest request) {

        if (!StringUtils.hasText(request.getProvinceId())) {
            return new ListResponse<>();
        }

        List<DistrictsEntity> list = districtsRepository.findFirstByProvinceId(request.getProvinceId());

        log.info("Total district data size:{}", list.size());

        List<LocationResponse> content = list.stream()
                .map(this::toDistrictResponse)
                .toList();

        return ListResponse.<LocationResponse>builder()
                .content(content)
                .totalElements((long) content.size())
                .totalPages(content.isEmpty() ? 0 : 1)
                .build();

    }

    @Override
    public ListResponse<LocationResponse> getCommune(LocationRequest request) {
        if (!StringUtils.hasText(request.getDistrictId())) {
            return new ListResponse<>();
        }
        List<CommunesEntity> list = communesRepository.findFirstByDistrictId(request.getDistrictId());

        log.info("Total commune data size:{}", list.size());

        List<LocationResponse> content = list.stream()
                .map(this::toCommuneResponse)
                .toList();

        return ListResponse.<LocationResponse>builder()
                .content(content)
                .totalElements((long) content.size())
                .totalPages(content.isEmpty() ? 0 : 1)
                .build();

    }

    @Override
    public ListResponse<LocationResponse> getVillage(LocationRequest request) {

        if (!StringUtils.hasText(request.getCommuneId())) {
            return new ListResponse<>();
        }
        List<VillagesEntity> list = villageRepository.findFirstByCommuneId(request.getCommuneId());

        log.info("Total village data size:{}", list.size());
        List<LocationResponse> content = list.stream()
                .map(this::toVillageResponse)
                .toList();

        return ListResponse.<LocationResponse>builder()
                .content(content)
                .totalElements((long) content.size())
                .totalPages(content.isEmpty() ? 0 : 1)
                .build();

    }

    private LocationResponse toProvinceResponse(ProvincesEntity province) {
        return LocationResponse.builder()
                .id(province.getId())
                .provinceId(province.getProvinceId())
                .provinceNameEn(province.getNameEn())
                .provinceNameKh(province.getNameKm())
                .build();
    }

    private LocationResponse toDistrictResponse(DistrictsEntity province) {
        return LocationResponse.builder()
                .id(province.getId())
                .provinceId(province.getProvinceId())
                .provinceNameEn(province.getNameEn())
                .provinceNameKh(province.getNameKm())
                .build();
    }

    private LocationResponse toCommuneResponse(CommunesEntity province) {
        return LocationResponse.builder()
                .id(province.getId())
                .districtId(province.getDistrictId())
                .communeId(province.getCommuneId())
                .communeNameEn(province.getNameEn())
                .communeNameKh(province.getNameKm())
                .build();
    }

    private LocationResponse toVillageResponse(VillagesEntity province) {
        return LocationResponse.builder()
                .id(province.getId())
                .communeId(province.getCommuneId())
                .villageNameEn(province.getNameEn())
                .villageNameKh(province.getNameKm())
                .build();
    }

}
