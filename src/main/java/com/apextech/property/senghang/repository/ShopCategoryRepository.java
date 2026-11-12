package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.ShopCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategoryEntity, Long> {

    boolean existsByCategoryCode(String categoryCode);

    Optional<ShopCategoryEntity> findFirstByCategoryCode(String categoryCode);

    Page<ShopCategoryEntity> findAllByOrderByIdDesc(Pageable pageable);
}
