package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Profile;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ProfileRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository; // Novo repositório
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, 
                       ProfileRepository profileRepository, 
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        // 1. Criptografa a senha
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 2. Busca o perfil padrão no banco
        Profile defaultProfile = profileRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Erro: Perfil ROLE_USER não encontrado no banco!"));

        // 3. Associa o perfil ao usuário
        user.setRole("ROLE_ADMIN");

        return userRepository.save(user);
    }
}