package com.example.demo.services.user;

import com.example.demo.models.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    void create(User user);

    User findByUsername(String username);

}
