package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.exceptions.TokenRefreshException;
import com.example.waa_first_demo.service.security.AuthService;
import com.example.waa_first_demo.util.LoginRequest;
import com.example.waa_first_demo.util.LoginResponse;
import com.example.waa_first_demo.util.RefreshTokenRequest;
import com.example.waa_first_demo.util.RefreshTokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/token/refresh")
    RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws TokenRefreshException {
        return authService.refreshToken(refreshTokenRequest);
    }

}
