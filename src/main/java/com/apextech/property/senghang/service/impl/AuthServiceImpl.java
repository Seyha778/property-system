package com.apextech.property.senghang.service.impl;

import com.apextech.property.senghang.common.ResponseMessageBuilder;
import com.apextech.property.senghang.entity.SubscriptionPlanEntity;
import com.apextech.property.senghang.entity.UserEntity;
import com.apextech.property.senghang.entity.UserSubscriptionEntity;
import com.apextech.property.senghang.exception.UnauthorizedException;
import com.apextech.property.senghang.model.request.LoginRequest;
import com.apextech.property.senghang.model.response.LoginResponse;
import com.apextech.property.senghang.model.response.LoginSubscriptionResponse;
import com.apextech.property.senghang.model.response.LoginUserResponse;
import com.apextech.property.senghang.repository.ShopListingsRepository;
import com.apextech.property.senghang.repository.UserRepository;
import com.apextech.property.senghang.repository.UserRoleRepository;
import com.apextech.property.senghang.repository.UserSubscriptionRepository;
import com.apextech.property.senghang.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserSubscriptionRepository subscriptionRepository;
    private final ShopListingsRepository listingRepository;

    //private final PasswordEncoder passwordEncoder;
    //private final JwtService jwtService;

    @Override
    public LoginResponse login(
            LoginRequest request) {

        // 1. Find user
        UserEntity user = userRepository
                .findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid phone number or password"));

        // 2. Verify password
//        if (!passwordEncoder.matches(
//                request.getPassword(), user.getPassword())) {
//            throw new UnauthorizedException(
//                    "Invalid phone number or password");
//        }

        // 3. Check account status
        if (!"ACTIVE".equals(user.getStatus())) {
            throw new UnauthorizedException("Account is not active");
        }

        // 4. Get user roles
        List<String> roles = userRoleRepository.findRoleCodesByUserId(user.getId());

        // 5. Generate tokens
        //String accessToken = jwtService.generateAccessToken(user);
        //String refreshToken = jwtService.generateRefreshToken(user);

        // 6. Get subscription information
        LoginSubscriptionResponse subscription =
                getSubscriptionInfo(user.getId());

        // 7. Build user response
        LoginUserResponse userResponse =
                LoginUserResponse.builder()
                        .userCode(user.getUserId())
                        .fullName(user.getFullName())
                        .phoneNumber(user.getPhoneNumber())
                        .email(user.getEmail())
                        .profileImageUrl(user.getProfileImageUrl())
                        .gender(user.getGender())
                        .roles(roles)
                        .build();

        // 8. Build login response
        return LoginResponse.builder()
                .accessToken("")
                .refreshToken("")
                .tokenType("Bearer")
                .expiresIn(3600L)
                .user(userResponse)
                .subscription(subscription)
                .build();
    }

    private LoginSubscriptionResponse getSubscriptionInfo(Long userId) {

        UserSubscriptionEntity userSubscription =
                subscriptionRepository
                        .findFirstByUserIdAndStatusOrderByEndDateDesc(
                                userId, "ACTIVE")
                        .filter(s -> s.getEndDate().isAfter(OffsetDateTime.now()))
                        .orElse(null);

        // TODO: Resolve your default FREE plan if no active subscription exists.
        if (userSubscription == null) {
            throw new IllegalStateException(
                    "Default FREE subscription must be assigned to every user");
        }

        //SubscriptionPlanEntity plan = userSubscription.getPlan();

        int activePosts = Math.toIntExact(
                listingRepository.countBySellerIdAndStatus(String.valueOf(userId), "ACTIVE"));

        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime monthStart = now.toLocalDate()
                .withDayOfMonth(1).atStartOfDay().atOffset(now.getOffset());
        OffsetDateTime nextMonth = monthStart.plusMonths(1);

        int monthlyPosts = Math.toIntExact(
                listingRepository
                        .countBySellerIdAndCreatedOnGreaterThanEqualAndCreatedOnLessThan(
                                String.valueOf(userId), monthStart, nextMonth));

        return LoginSubscriptionResponse.builder()
                //.planCode(plan.getPlanCode())
                //.planName(plan.getPlanName())
                .status(userSubscription.getStatus())
                .startDate(userSubscription.getStartDate().toLocalDate())
                .endDate(userSubscription.getEndDate().toLocalDate())
                //.maxActivePosts(plan.getMaxActivePosts())
                //.maxPostsPerMonth(plan.getMaxPostsPerMonth())
                .activePosts(activePosts)
                .postsCreatedThisMonth(monthlyPosts)
//                .remainingActivePosts(
//                        plan.getMaxActivePosts() == null ? null :
//                                Math.max(0, plan.getMaxActivePosts() - activePosts))
//                .remainingMonthlyPosts(
//                        plan.getMaxPostsPerMonth() == null ? null :
//                                Math.max(0, plan.getMaxPostsPerMonth() - monthlyPosts))
                .build();
    }
}