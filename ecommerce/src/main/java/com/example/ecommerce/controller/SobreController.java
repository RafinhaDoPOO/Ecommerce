package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SobreController {

    @GetMapping("/sobre")
    public String abt() {
            return "Esta é a página sobre o e-commerce!";
        }
    }
    

