package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email); // Método para buscar usuário por email
}