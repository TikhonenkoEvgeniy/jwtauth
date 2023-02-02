package com.example.jwtauth.service;

import com.example.jwtauth.entity.Credential;
import lombok.NonNull;

public interface RegistrationService {
    void add(@NonNull Credential credential);
}
