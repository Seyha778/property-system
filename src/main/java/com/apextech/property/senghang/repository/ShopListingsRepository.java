package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.ShopListingsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface ShopListingsRepository extends JpaRepository<ShopListingsEntity, Long> {
    boolean existsByListingCode(String ListingCode);
    Optional<ShopListingsEntity> findFirstByListingCode(String productCode);

    Page<ShopListingsEntity> findAllByOrderByIdDesc(Pageable pageable);

    Page<ShopListingsEntity> findAllBySellerId(String sellerId, Pageable pageable);

    long countBySellerIdAndStatus(String userId, String status);

    long countBySellerIdAndCreatedOnGreaterThanEqualAndCreatedOnLessThan(
            String userId,
            OffsetDateTime start,
            OffsetDateTime end
    );

//    @Query("""
//        SELECT
//        p.id AS id,
//        p.productCode AS productCode,
//        p.title AS title,
//        c.propertyType AS propertyType,
//        c.propertyName AS propertyName,
//        p.description AS description,
//        p.salePrice AS salePrice,
//        p.salePrice AS salePrice,
//        p.comparePrice AS comparePrice,
//        p.currency AS currency,
//        p.status AS status,
//        p.isFeatured AS isFeatured
//        FROM ShopEntity p
//        LEFT JOIN ShopCategoryEntity c ON c.categoryCode = p.categoryId
//         WHERE
//              (:categoryId IS NULL OR p.categoryId = :categoryId)
//              AND (:productStatus IS NULL OR p.productStatus = :productStatus)
//              AND (:keyword IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')))
//          ORDER BY p.id DESC
//    """)
//    Page<ProductResponse> findProducts(Pageable pageable, String categoryId, String productStatus
//            , String keyword);


    @Query(value = """
        SELECT nextval('property.listing_code_seq')
        """, nativeQuery = true)
    Long nextListingCodeSequence();

}
