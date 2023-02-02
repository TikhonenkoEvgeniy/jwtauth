package com.example.jwtauth.repo.impl;

import com.example.jwtauth.entity.JwtToken;
import com.example.jwtauth.repo.JwtTokenRepo;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
public class JwtTokenRepoImpl implements JwtTokenRepo {
    @Override
    public void save(JwtToken jwtToken) {

    }

    @Override
    public void delete(String login) {

    }

    @Override
    public JwtToken findToken(String login) {
        return null;
    }
}
