package com.example.waa_first_demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;

    private String refreshToken;
}
