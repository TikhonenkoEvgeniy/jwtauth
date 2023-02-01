package com.example.jwtauth.service.impl;

import com.example.jwtauth.entity.User;
import com.example.jwtauth.repo.UserRepo;
import com.example.jwtauth.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepo userRepo;

    @Override
    public void addUser(User user) {
        userRepo.save(user);
    }
}
