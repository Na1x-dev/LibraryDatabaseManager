package com.example.demo.repositories.user;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
