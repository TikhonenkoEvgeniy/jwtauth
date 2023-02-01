package com.example.jwtauth.entity;

import com.example.jwtauth.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String passwd;
    @Column(name = "is_active")
    private Boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "user_roles")
    private Set<Role> roles;

    @OneToOne(cascade = {CascadeType.ALL}, targetEntity = Token.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "token_id")
    private Token token;

    public User(String login, String passwd, Boolean active, Set<Role> roles) {
        this.login = login;
        this.passwd = passwd;
        this.active = active;
        this.roles = roles;
    }
}
