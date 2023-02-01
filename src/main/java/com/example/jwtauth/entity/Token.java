package com.example.jwtauth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "jwt_tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "access_token")
    private String access;
    @Column(name = "refresh_token")
    private String refresh;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "jwt_tokens_user",
            joinColumns = @JoinColumn(name = "token_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public Token(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }
}
