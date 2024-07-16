package com.example.Spring_Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

// whenever we want to create our own beans, we need '@Configuration' annotation
// http://localhost:8080/spring-security

@Configuration
public class SpringSecurityConfig {
    /*
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
     */

    /*
    @Bean
    UserDetailsManager users(DataSource dataSource) {
        // adding user using query:
        // insert into users (username, password, enabled) values ('test', 'test', 1);
        // insert into authorities (username, authority) values ('test', 'ADMIN');

        // only for DEMO
        UserDetails user = User.builder()
                .username("user")
                .password("password")
                .authorities("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .authorities("USER", "ADMIN")
                .build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        if (!users.userExists(user.getUsername())) {
            users.createUser(user);
        }
        if (!users.userExists(admin.getUsername())) {
            users.createUser(admin);
        }
        return users;
    }
     */

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
