package com.example.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de boas-vindas para o e-commerce.
 */
@RestController
public class HelloController {

    /**
     * Endpoint que retorna uma mensagem de boas-vindas ao e-commerce.
     *
     * @return Mensagem de boas-vindas com status HTTP 200 OK.
     */
    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        // Retorna a resposta com status 200 OK
        return new ResponseEntity<>("Bem-vindo ao meu e-commerce!", HttpStatus.OK);
    }
}
