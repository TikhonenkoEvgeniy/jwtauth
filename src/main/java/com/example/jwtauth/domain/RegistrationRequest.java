package com.example.jwtauth.domain;

import com.example.jwtauth.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class RegistrationRequest {
    private String login;
    private String passwd;
    private boolean active;
    private Set<Role> roles;
}