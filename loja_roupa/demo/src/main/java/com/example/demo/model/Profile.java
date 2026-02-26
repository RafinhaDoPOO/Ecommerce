package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Exemplo: "ROLE_ADMIN" ou "ROLE_USER"
    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
}
}