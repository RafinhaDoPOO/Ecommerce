package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Exibe a tela de cadastro
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    // Salva o novo usuário
    @PostMapping("/register")
    public String registerUser(User user) {
        // Criptografa a senha antes de mandar para o banco!
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Força a permissão padrão como ADMIN para os seus testes
        user.setRole("ROLE_ADMIN"); 
        
        userRepository.save(user);
        
        // Redireciona para o login
        return "redirect:/login";
    }
}