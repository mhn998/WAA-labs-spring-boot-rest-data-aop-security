package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.service.security.AuthService;
import com.example.waa_first_demo.util.LoginRequest;
import com.example.waa_first_demo.util.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping
    LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
