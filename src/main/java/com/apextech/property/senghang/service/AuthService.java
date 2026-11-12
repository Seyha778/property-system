package com.apextech.property.senghang.service;

import com.apextech.property.senghang.model.request.LoginRequest;
import com.apextech.property.senghang.model.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
