package com.example.jwtauth.repo.impl;

import com.example.jwtauth.entity.JwtToken;
import com.example.jwtauth.repo.JwtTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
@RequiredArgsConstructor
public class JwtTokenRepoImpl implements JwtTokenRepo {
    private static final String TOKEN = "token";

    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(JwtToken jwtToken) {
        redisTemplate.opsForHash().put(TOKEN, jwtToken.getLogin(), jwtToken);
    }

    @Override
    public void delete(String login) {
        redisTemplate.opsForHash().delete(TOKEN, login);
    }

    @Override
    public JwtToken findToken(String login) {
        return (JwtToken) redisTemplate.opsForHash().get(TOKEN, login);
    }
}
