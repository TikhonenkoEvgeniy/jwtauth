package com.example.jwtauth.service.impl;

import com.example.jwtauth.entity.Credential;
import com.example.jwtauth.repo.CredentialRepo;
import com.example.jwtauth.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final CredentialRepo credentialRepo;

    @Override
    public void add(Credential credential) {
        credentialRepo.save(credential);
    }
}
