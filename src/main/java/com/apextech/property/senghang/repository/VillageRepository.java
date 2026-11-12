package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.VillagesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<VillagesEntity, Long> {
    boolean existsById(String id);
    List<VillagesEntity> findFirstByCommuneId(String communeId);
    List<VillagesEntity> findByCommuneIdOrderByNameEnAsc(String communeId);

    Page<VillagesEntity> findAllByOrderByIdDesc(Pageable pageable);

}
