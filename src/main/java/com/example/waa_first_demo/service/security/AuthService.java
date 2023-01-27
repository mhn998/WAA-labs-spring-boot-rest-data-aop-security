package com.example.waa_first_demo.service.security;

import com.example.waa_first_demo.util.LoginRequest;
import com.example.waa_first_demo.util.LoginResponse;
import com.example.waa_first_demo.util.RefreshTokenRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
