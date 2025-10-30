package com.example.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para gerenciar a página do perfil do usuário.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Endpoint para obter o perfil do usuário.
     *
     * @return Mensagem de perfil do usuário.
     */
    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        // Retorna uma resposta com status 200 OK
        return ResponseEntity.ok("User Profile");
    }
}
