package com.example.Spring_Security.controller;

import com.example.Spring_Security.model.CustomSecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomSecurityUser user =  (CustomSecurityUser) authentication.getPrincipal();
        return "User Route for ".concat(user.getUsername());
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin Route";
    }
}
