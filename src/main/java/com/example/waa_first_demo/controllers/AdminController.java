package com.example.waa_first_demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    ResponseEntity<?> HelloAdmin(SecurityContextHolderAwareRequestWrapper request) {
        boolean b = request.isUserInRole("ROLE_ADMIN");
        System.out.println("ROLE_ADMIN=" + b);

        boolean c = request.isUserInRole("ROLE_USER");
        System.out.println("ROLE_USER=" + c);
        return ResponseEntity.ok("Hello Admin");
    }
}
