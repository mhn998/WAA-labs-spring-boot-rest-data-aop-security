package com.example.waa_first_demo.util;

import com.example.waa_first_demo.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponse {
    Long id;

    private String email;

    private List<Role> roles;


    @NonNull
    private String accessToken;

    @NonNull
    private String refreshToken;

}
