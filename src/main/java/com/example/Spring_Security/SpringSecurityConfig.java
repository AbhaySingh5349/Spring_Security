package com.example.Spring_Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// whenever we want to create our own beans, we need '@Configuration' annotation
// http://localhost:8080/spring-security

@Configuration
public class SpringSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("password")
                .authorities("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .authorities("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    // to specify which API can be accessed different authorities (authorization for routes)
    // API should have at least 1 of authorities assigned to users
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/home/**").permitAll() // even if user is un-authenticated, access is allowed
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .requestMatchers("/user").hasAuthority("USER")
                        .requestMatchers("/spring-security").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().permitAll())
                .formLogin(withDefaults()) // we need a login form for accessing authorized routes (browser)
                .httpBasic(withDefaults()); // for postman, we need to provide "Basic Auth"
        return http.build();
    }

    // password encoding
    @Bean
    public PasswordEncoder getEncoder(){
        return NoOpPasswordEncoder.getInstance(); // no encryption is done for user & password, plain text comparison
    }

}
