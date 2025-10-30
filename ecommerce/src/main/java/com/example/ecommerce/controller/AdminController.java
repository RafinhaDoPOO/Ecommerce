package com.example.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para acessar recursos do painel administrativo.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     * Endpoint para obter o painel administrativo.
     *
     * @return Retorna uma resposta de sucesso com a mensagem do painel.
     */
    @GetMapping("/dashboard")
    public ResponseEntity<String> getAdminDashboard() {
        // Retorna a resposta com status 200 OK
        return new ResponseEntity<>("Admin Dashboard", HttpStatus.OK);
    }
}
