package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.CommunesEntity;
import com.apextech.property.senghang.entity.ShopListingsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunesRepository extends JpaRepository<CommunesEntity, Long> {
    boolean existsByCommuneId(String communeId);
    List<CommunesEntity> findFirstByDistrictId(String districtId);
    Page<ShopListingsEntity> findAllByOrderByIdDesc(Pageable pageable);
    List<CommunesEntity> findByDistrictIdOrderByNameEnAsc(String pageable);
}
