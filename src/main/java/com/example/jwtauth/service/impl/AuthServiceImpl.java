package com.example.jwtauth.service.impl;

import com.example.jwtauth.JwtProvider;
import com.example.jwtauth.domain.JwtAuthentication;
import com.example.jwtauth.domain.JwtRequest;
import com.example.jwtauth.domain.JwtResponse;
import com.example.jwtauth.entity.Credential;
import com.example.jwtauth.entity.JwtToken;
import com.example.jwtauth.exception.AuthException;
import com.example.jwtauth.repo.JwtTokenRepo;
import com.example.jwtauth.service.AuthService;
import com.example.jwtauth.service.CredentialService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenRepo jwtTokenRepo;
    private final CredentialService credentialService;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) throws CredentialNotFoundException {
        Credential credential = credentialService.getByLogin(authRequest.getLogin());
        if (credential.getPasswd().equals(authRequest.getPasswd())) {
            JwtToken jwtToken = new JwtToken(credential.getLogin(),
                    jwtProvider.generateAccessToken(credential),
                    jwtProvider.generateRefreshToken(credential));
            jwtTokenRepo.save(jwtToken);
            return new JwtResponse(jwtToken.getAccess(), jwtToken.getRefresh());
        }
        else {
            throw new AuthException("Err: Incorrect password");
        }
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) throws CredentialNotFoundException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Credential credential = credentialService.getByLogin(
                    jwtProvider.getRefreshClaims(refreshToken).getSubject());
            JwtToken jwtToken = jwtTokenRepo.findToken(credential.getLogin());

            if (jwtToken.getRefresh() != null && jwtToken.getRefresh().equals(refreshToken)) {
                jwtToken.setAccess(jwtProvider.generateAccessToken(credential));
                jwtTokenRepo.save(jwtToken);
                return new JwtResponse(jwtToken.getAccess(), null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) throws CredentialNotFoundException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Credential credential = credentialService.getByLogin(
                    jwtProvider.getRefreshClaims(refreshToken).getSubject());
            JwtToken jwtToken = jwtTokenRepo.findToken(credential.getLogin());

            if (jwtToken.getRefresh() != null && jwtToken.getRefresh().equals(refreshToken)) {
                jwtToken.setAccess(jwtProvider.generateAccessToken(credential));
                jwtToken.setRefresh(jwtProvider.generateRefreshToken(credential));
                jwtTokenRepo.save(jwtToken);
                return new JwtResponse(jwtToken.getAccess(), jwtToken.getRefresh());
            }
        }
        throw new AuthException("Err: invalid token");
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
