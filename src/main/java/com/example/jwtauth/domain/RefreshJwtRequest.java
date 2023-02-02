package com.example.jwtauth.domain;

import lombok.Data;

@Data
public class RefreshJwtRequest {
    public String refreshToken;
}
