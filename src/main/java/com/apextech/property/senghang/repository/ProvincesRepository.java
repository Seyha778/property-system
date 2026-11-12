package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.ProvincesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincesRepository extends JpaRepository<ProvincesEntity, Long> {
    boolean existsByProvinceId(String provinceId);
    Page<ProvincesEntity> findAllByOrderByIdDesc(Pageable pageable);

}
