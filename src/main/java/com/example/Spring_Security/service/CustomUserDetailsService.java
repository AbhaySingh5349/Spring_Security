package com.example.Spring_Security.service;

import com.example.Spring_Security.model.CustomSecurityUser;
import com.example.Spring_Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new User("abc", "abc", Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));

        // insert into customsecurityuser(username, password, authorities) values ('abhay', 'abhay', 'ADMIN,USER,TEST');

        CustomSecurityUser user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(username.concat(" user name not found"));
        }

        return user;
    }
}
