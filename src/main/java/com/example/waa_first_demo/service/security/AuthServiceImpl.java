package com.example.waa_first_demo.service.security;

import com.example.waa_first_demo.service.user.UserService;
import com.example.waa_first_demo.util.JwtUtil;
import com.example.waa_first_demo.util.LoginRequest;
import com.example.waa_first_demo.util.LoginResponse;
import com.example.waa_first_demo.util.RefreshTokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserService userService;
    AuthenticationManager authenticationManager;

    UserDetailsService userDetailsService;

    @Override
    public LoginResponse login(LoginRequest loginRequest)  {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = JwtUtil.generateToken(userDetails);
        final String refreshToken = JwtUtil.generateRefreshToken(loginRequest.getEmail());

        return new LoginResponse(accessToken, refreshToken);
    }


    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

}
