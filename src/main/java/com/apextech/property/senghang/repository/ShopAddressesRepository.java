package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.ShopAddressesEntity;
import com.apextech.property.senghang.model.response.LocationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopAddressesRepository extends JpaRepository<ShopAddressesEntity, Long> {

    @Query("""
        SELECT
        p.provinceId AS provinceId,
        d.districtId AS districtId,
        c.communeId AS communeId,
        l.road AS road,
        l.houseNumber AS houseNumber,
        p.nameEn AS provinceNameEn,
        p.nameKm AS provinceNameKh,
        d.nameEn AS districtNameEn,
        d.nameKm AS districtNameKh,
        c.nameEn AS communeNameEn,
        c.nameKm AS communeNameKh,
        v.nameEn AS villageNameEn,
        v.nameKm AS villageNameKh
        FROM ShopListingsEntity s
        INNER JOIN ShopAddressesEntity l ON l.listingCode = s.listingCode
        LEFT JOIN ProvincesEntity p ON p.provinceId = l.province
        LEFT JOIN DistrictsEntity d ON d.districtId = l.district
        LEFT JOIN CommunesEntity c ON c.communeId = l.commune
        LEFT JOIN VillagesEntity v ON v.villageId = l.village
         WHERE s.listingCode = :listingCode
    """)
    LocationResponse findAddressByListingCode(String listingCode);
}
