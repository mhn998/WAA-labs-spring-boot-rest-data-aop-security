package com.example.waa_first_demo.service.security;

import com.example.waa_first_demo.domain.security.UserDetailsCustom;
import com.example.waa_first_demo.exceptions.TokenRefreshException;
import com.example.waa_first_demo.domain.RefreshToken;
import com.example.waa_first_demo.service.user.UserService;
import com.example.waa_first_demo.util.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserService userService;
    AuthenticationManager authenticationManager;

    UserDetailsService userDetailsService;

    RefreshTokenService refreshTokenService;


    @Override
    public LoginResponse login(LoginRequest loginRequest)  {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsCustom userDetails = (UserDetailsCustom) authentication.getPrincipal();

        final String accessToken = JwtUtil.generateToken(userDetails);
        final RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails);


        return new LoginResponse(userDetails.getId(), userDetails.getEmail(), userDetails.getRoles(), accessToken, refreshToken.getRefreshToken());
    }





    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws TokenRefreshException {
        String requestRefreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map((refreshToken) -> {
                    try {
                        return refreshTokenService.verifyExpiration(refreshToken);
                    } catch (TokenRefreshException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = JwtUtil.generateToken(new UserDetailsCustom(user));
                    return new RefreshTokenResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

}
