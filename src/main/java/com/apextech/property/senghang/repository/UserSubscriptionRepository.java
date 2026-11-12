package com.apextech.property.senghang.repository;

import com.apextech.property.senghang.entity.UserSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubscriptionRepository
        extends JpaRepository<UserSubscriptionEntity, Long> {

    Optional<UserSubscriptionEntity> findBySubscriptionCode(String subscriptionCode);

    List<UserSubscriptionEntity> findByUserId(Long userId);

    List<UserSubscriptionEntity> findByUserIdOrderByCreatedOnDesc(Long userId);

    Optional<UserSubscriptionEntity> findFirstByUserIdAndStatusOrderByEndDateDesc(Long userId, String status);

    Optional<UserSubscriptionEntity>
    findFirstByUserIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByEndDateDesc(
            Long userId,
            String status,
            OffsetDateTime now,
            OffsetDateTime currentTime
    );

    boolean existsByUserIdAndStatus(Long userId, String status);

    List<UserSubscriptionEntity> findByStatusAndEndDateBefore(
            String status,
            OffsetDateTime dateTime
    );
}