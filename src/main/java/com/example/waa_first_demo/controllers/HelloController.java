package com.example.waa_first_demo.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {


    @RequestMapping
    public String printHello() {
        return "Welcome back Spring!";
    }

    public static void main(String[] args) {
        // this is created for demo purposes!
    }

}
