package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Pega quem entrou na página vazia e joga para o catálogo
        return "redirect:/products/list";
    }
}