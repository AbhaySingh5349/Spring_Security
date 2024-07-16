package com.example.Spring_Security.repository;

import com.example.Spring_Security.model.CustomSecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomSecurityUser, Integer> {
    CustomSecurityUser findByUsername(String username);
}
