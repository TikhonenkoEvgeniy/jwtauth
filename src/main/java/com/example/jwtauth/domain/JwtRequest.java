package com.example.jwtauth.domain;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String passwd;
}
