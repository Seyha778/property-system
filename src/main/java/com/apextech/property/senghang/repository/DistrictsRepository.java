package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.DistrictsEntity;
import com.apextech.property.senghang.entity.ShopListingsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictsRepository extends JpaRepository<DistrictsEntity, Long> {
    boolean existsByDistrictId(String ListingCode);
    List<DistrictsEntity> findFirstByProvinceId(String provinceId);

    Page<ShopListingsEntity> findAllByOrderByIdDesc(Pageable pageable);

    List<DistrictsEntity> findByProvinceIdOrderByNameEnAsc(String id);

}
