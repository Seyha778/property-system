package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.ShopFavoritesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopFavoritesRepository extends JpaRepository<ShopFavoritesEntity, Long> {
    Page<ShopFavoritesEntity> findAllByUserId(String userId, Pageable pageable);
}
