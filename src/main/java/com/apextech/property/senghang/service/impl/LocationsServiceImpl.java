package com.apextech.property.senghang.service.impl;

import com.apextech.property.senghang.common.ResponseMessageBuilder;
import com.apextech.property.senghang.model.response.LocationResponse;
import com.apextech.property.senghang.repository.CommunesRepository;
import com.apextech.property.senghang.repository.DistrictsRepository;
import com.apextech.property.senghang.repository.ProvincesRepository;
import com.apextech.property.senghang.repository.VillageRepository;
import com.apextech.property.senghang.service.LocationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationsServiceImpl implements LocationsService {

    private final ProvincesRepository provinceRepository;
    private final DistrictsRepository districtRepository;
    private final CommunesRepository communeRepository;
    private final VillageRepository villageRepository;

    public List<LocationResponse> getProvinces() {

        return provinceRepository.findAll(
                        Sort.by(Sort.Direction.ASC, "nameEn"))
                .stream()
                .map(p -> LocationResponse.builder()
                        .provinceId(p.getProvinceId())
                        .nameEn(p.getNameEn())
                        .nameKm(p.getNameKm())
                        .build())
                .toList();
    }

    public List<LocationResponse> getDistricts(
            String provinceId) {

        return districtRepository
                        .findByProvinceIdOrderByNameEnAsc(provinceId)
                        .stream()
                        .map(d -> LocationResponse.builder()
                                .districtId(d.getDistrictId())
                                .provinceId(d.getProvinceId())
                                .nameEn(d.getNameEn())
                                .nameKm(d.getNameKm())
                                .build())
                        .toList();
    }

    public List<LocationResponse> getCommunes(
            String districtId) {

        return communeRepository
                        .findByDistrictIdOrderByNameEnAsc(districtId)
                        .stream()
                        .map(c -> LocationResponse.builder()
                                .communeId(c.getCommuneId())
                                .districtId(c.getDistrictId())
                                .nameEn(c.getNameEn())
                                .nameKm(c.getNameKm())
                                .build())
                        .toList();
    }

    public List<LocationResponse> getVillages(String communeId) {
        return villageRepository
                        .findByCommuneIdOrderByNameEnAsc(communeId)
                        .stream()
                        .map(v -> LocationResponse.builder()
                                .villageId(v.getVillageId())
                                .communeId(v.getCommuneId())
                                .nameEn(v.getNameEn())
                                .nameKm(v.getNameKm())
                                .build())
                        .toList();
    }
}