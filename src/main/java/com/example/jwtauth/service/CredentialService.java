package com.example.jwtauth.service;

import com.example.jwtauth.entity.Credential;

import javax.security.auth.login.CredentialNotFoundException;

public interface CredentialService {
    Credential getByLogin(String login) throws CredentialNotFoundException;
    boolean isExistByLogin(String login);
    void save(Credential credential) throws CredentialNotFoundException;
}
