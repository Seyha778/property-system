package com.apextech.property.senghang.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class LoginUserResponse {

    private String userCode;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String profileImageUrl;
    private String gender;
    private List<String> roles;
}