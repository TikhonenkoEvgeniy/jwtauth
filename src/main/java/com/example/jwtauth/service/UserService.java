package com.example.jwtauth.service;

import com.example.jwtauth.entity.User;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getByLogin(@NonNull String login);
    void save(@NonNull User user);
}
