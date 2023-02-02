package com.example.jwtauth.repo;

import com.example.jwtauth.entity.JwtToken;

public interface JwtTokenRepo {
    void save(JwtToken jwtToken);
    void delete(String login);
    JwtToken findToken(String login);
}
