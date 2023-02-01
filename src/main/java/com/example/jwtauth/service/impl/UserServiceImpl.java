package com.example.jwtauth.service.impl;

import com.example.jwtauth.entity.User;
import com.example.jwtauth.repo.UserRepo;
import com.example.jwtauth.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public User getByLogin(@NonNull String login) {
        return userRepo.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException(login + " was not founded"));
    }

    @Override
    public void save(@NonNull User user) {
        userRepo.save(user);
    }
}
