package com.example.jwtauth.service;

import com.example.jwtauth.domain.JwtAuthentication;
import com.example.jwtauth.domain.JwtRequest;
import com.example.jwtauth.domain.JwtResponse;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;

import javax.security.auth.login.CredentialNotFoundException;
import java.security.NoSuchAlgorithmException;

public interface AuthService {
    JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException, CredentialNotFoundException, NoSuchAlgorithmException;
    JwtResponse getAccessToken(@NonNull String refreshToken) throws CredentialNotFoundException;
    JwtResponse refresh(@NonNull String refreshToken) throws CredentialNotFoundException;
    JwtAuthentication getAuthInfo();
}
