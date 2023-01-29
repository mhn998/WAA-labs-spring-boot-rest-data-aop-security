package com.example.waa_first_demo.service.security;

import com.example.waa_first_demo.exceptions.TokenRefreshException;
import com.example.waa_first_demo.util.LoginRequest;
import com.example.waa_first_demo.util.LoginResponse;
import com.example.waa_first_demo.util.RefreshTokenRequest;
import com.example.waa_first_demo.util.RefreshTokenResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws TokenRefreshException;
}
