package com.example.jwtauth.service;

import com.example.jwtauth.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    void addUser(User user);
}
