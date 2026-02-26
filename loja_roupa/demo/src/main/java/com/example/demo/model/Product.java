package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double price; // Usar Double (objeto) evita erros de mapeamento
    private String size;
    private String imageUrl;
    private Integer qty_stock;
    private String description;

    // GETTERS (Essenciais para o CartController e Thymeleaf)
    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public String getSize() { return size; }
    public String getImageUrl() { return imageUrl; }
    public Integer getQty_stock() { return qty_stock; }
    public String getDescription() { return description; }

    // SETTERS (Essenciais para o Cadastro/Edição)
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(Double price) { this.price = price; }
    public void setSize(String size) { this.size = size; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setQty_stock(Integer qty_stock) { this.qty_stock = qty_stock; }
    public void setDescription(String description) { this.description = description; }
}