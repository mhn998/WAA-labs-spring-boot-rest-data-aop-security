package com.example.waa_first_demo.service.security;

import com.example.waa_first_demo.domain.RefreshToken;
import com.example.waa_first_demo.domain.security.UserDetailsCustom;
import com.example.waa_first_demo.exceptions.TokenRefreshException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException;
    RefreshToken createRefreshToken(UserDetailsCustom userDetails);

}
