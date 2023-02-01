package com.example.jwtauth.service;

import com.example.jwtauth.domain.JwtAuthentication;
import com.example.jwtauth.domain.JwtRequest;
import com.example.jwtauth.domain.JwtResponse;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException;
    JwtResponse getAccessToken(@NonNull String refreshToken);
    JwtResponse refresh(@NonNull String refreshToken);
    JwtAuthentication getAuthInfo();
}
