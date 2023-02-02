package com.example.jwtauth.repo;

import com.example.jwtauth.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepo extends JpaRepository<Credential, Long> {
    Optional<Credential> findByLogin(String login);
}
