package com.example.demo.repository;

import com.example.demo.model.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // O retorno deve ser a própria entidade Profile
    Optional<Profile> findByName(String name); 
}