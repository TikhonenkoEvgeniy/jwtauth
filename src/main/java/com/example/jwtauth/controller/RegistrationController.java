package com.example.jwtauth.controller;

import com.example.jwtauth.domain.RegistrationRequest;
import com.example.jwtauth.entity.Credential;
import com.example.jwtauth.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody RegistrationRequest request) {
        Credential credential = new Credential(
                request.getLogin(), request.getPasswd().getBytes(), request.isActive(), request.getRoles());
        registrationService.add(credential);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
