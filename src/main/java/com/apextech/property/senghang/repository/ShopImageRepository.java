package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.ShopImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopImageRepository extends JpaRepository<ShopImageEntity, Long> {

}
