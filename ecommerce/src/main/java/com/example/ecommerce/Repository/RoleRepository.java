package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name); // Método para buscar role por nome
}