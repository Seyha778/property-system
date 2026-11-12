package com.apextech.property.senghang.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LoginSubscriptionResponse {

    private String planCode;
    private String planName;
    private String status;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer maxActivePosts;
    private Integer maxPostsPerMonth;

    private Integer activePosts;
    private Integer postsCreatedThisMonth;

    private Integer remainingActivePosts;
    private Integer remainingMonthlyPosts;
}