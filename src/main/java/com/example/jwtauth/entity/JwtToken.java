package com.example.jwtauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("JwtTokens")
public class JwtToken implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    private String login;
    private String access;
    private String refresh;
}
