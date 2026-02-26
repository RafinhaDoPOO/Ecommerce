package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

   @GetMapping
public List<Product> listAll() {
    return service.findAll(); 
}
    // Trocamos @RequestBody por @ModelAttribute para aceitar o formulário
    @PostMapping
    public RedirectView create(@ModelAttribute Product product, 
                               @RequestParam("imageFile") MultipartFile imageFile) {
        
        if (!imageFile.isEmpty()) {
            try {
                // Caminho dentro do seu projeto no Linux
                String uploadDir = "src/main/resources/static/images/products/";
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);
                
                // Cria a pasta caso não exista
                Files.createDirectories(path.getParent());
                
                // Salva o arquivo físico
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                
                // Salva o caminho da URL no banco
                product.setImageUrl("/images/products/" + fileName);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        service.saveProduct(product);
        // Redireciona para o catálogo em vez de mostrar um JSON na tela
        return new RedirectView("/products/list");
    }

    @PatchMapping("/{id}/stock")
    public Product updateStock(@PathVariable Long id, @RequestParam Integer quantity) {
        return service.updateStock(id, quantity);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.deleteProduct(id);
    return ResponseEntity.noContent().build();
}
}
