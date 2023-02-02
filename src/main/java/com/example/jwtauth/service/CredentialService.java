package com.example.jwtauth.service;

import com.example.jwtauth.entity.Credential;
import lombok.NonNull;

import javax.security.auth.login.CredentialNotFoundException;

public interface CredentialService {
    Credential getByLogin(@NonNull String login) throws CredentialNotFoundException;
    boolean isExistByLogin(@NonNull String login);
    void save(@NonNull Credential credential) throws CredentialNotFoundException;
}
