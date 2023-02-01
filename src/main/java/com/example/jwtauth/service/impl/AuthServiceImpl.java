package com.example.jwtauth.service.impl;

import com.example.jwtauth.JwtProvider;
import com.example.jwtauth.domain.JwtAuthentication;
import com.example.jwtauth.domain.JwtRequest;
import com.example.jwtauth.domain.JwtResponse;
import com.example.jwtauth.entity.Token;
import com.example.jwtauth.entity.User;
import com.example.jwtauth.exception.AuthException;
import com.example.jwtauth.service.AuthService;
import com.example.jwtauth.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userService.getByLogin(authRequest.getLogin());
        if (user.getPasswd().equals(authRequest.getPasswd())) {
            user.setToken(new Token(jwtProvider.generateAccessToken(user),
                    jwtProvider.generateRefreshToken(user)));
            userService.save(user);
            return new JwtResponse(user.getToken().getAccess(), user.getToken().getRefresh());
        }
        else {
            throw new AuthException("Err: Incorrect password");
        }
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            User user = userService.getByLogin(jwtProvider.getRefreshClaims(refreshToken).getSubject());
            if (user.getToken().getRefresh() != null && user.getToken().getRefresh().equals(refreshToken)) {
                return new JwtResponse(
                        jwtProvider.generateAccessToken(user), null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            User user = userService.getByLogin(jwtProvider.getRefreshClaims(refreshToken).getSubject());
            if (user.getToken().getRefresh() != null && user.getToken().getRefresh().equals(refreshToken)) {
                user.getToken().setRefresh(jwtProvider.generateRefreshToken(user));
                userService.save(user);
                return new JwtResponse(jwtProvider.generateAccessToken(user), user.getToken().getRefresh());
            }
        }
        throw new AuthException("Err: invalid token");
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
