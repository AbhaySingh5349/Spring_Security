package com.example.Spring_Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/spring-security")
    public String springSecurity(){
        return "Spring Security Route";
    }

    @GetMapping("/home")
    public String home(){
        return "Home Route";
    }

    @GetMapping("/user")
    public String user(){
        return "User Route";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin Route";
    }
}
