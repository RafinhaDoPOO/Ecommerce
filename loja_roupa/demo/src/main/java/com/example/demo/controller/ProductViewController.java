package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products") // Diferente do /api/products para não conflitar
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }   

 

@GetMapping("/edit/{id}")
public String showEditForm(@PathVariable Long id, Model model) {
    Product product = productService.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    model.addAttribute("product", product);
    return "product-form"; // Reutilizamos o mesmo HTML de cadastro!
}

@GetMapping("/list")
public String listProducts(@RequestParam(value = "search", required = false) String search, Model model) {
    List<Product> products;
    if (search != null && !search.isEmpty()) {
        products = productService.searchByName(search);
    } else {
        products = productService.findAll();
    }
    model.addAttribute("products", products);
    return "product-list";
}


        @GetMapping("/new")
    public String showForm(Model model) {
        // Passamos um objeto vazio para o formulário "preencher"
        model.addAttribute("product", new Product());
        return "product-form"; // Nome do arquivo HTML (sem o .html)
    }


    @GetMapping("/{id}")
public String showDetails(@PathVariable Long id, Model model) {
    // findById já existe no seu repository por causa do JpaRepository
    Product product = productService.findById(id) 
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    model.addAttribute("product", product);
    return "product-details";
}
}