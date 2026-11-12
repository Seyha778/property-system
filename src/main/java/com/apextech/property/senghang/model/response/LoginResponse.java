package com.apextech.property.senghang.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;

    private LoginUserResponse user;

    private LoginSubscriptionResponse subscription;
}