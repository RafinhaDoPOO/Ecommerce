package com.example.demo.repository;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Busca produtos que contenham o termo no nome, ignorando maiúsculas/minúsculas
    List<Product> findByNameContainingIgnoreCase(String name);
}


