package com.example.jwtauth.service.impl;

import com.example.jwtauth.entity.Credential;
import com.example.jwtauth.repo.CredentialRepo;
import com.example.jwtauth.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepo credentialRepo;

    @Override
    public Credential getByLogin(String login) throws CredentialNotFoundException {
        return credentialRepo.findByLogin(login).orElseThrow(
                () -> new CredentialNotFoundException("Err: " + login + " was not found.")
        );
    }

    @Override
    public boolean isExistByLogin(String login) {
        return credentialRepo.findByLogin(login).isPresent();
    }

    @Override
    public void save(Credential credential) throws CredentialNotFoundException {
        if (isExistByLogin(credential.getLogin())) {
            credential.setId(getByLogin(credential.getLogin()).getId());
        }
        credentialRepo.save(credential);
    }
}
