package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.repository.ProductRepository;
import com.example.demo.model.Product;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Use este método para o catálogo. Ele chama o findAll() do JpaRepository.
    public List<Product> findAll() { 
        return repository.findAll();
    }

    public Product saveProduct(Product product) {
        if (product.getQty_stock() == null || product.getQty_stock() < 0) {
            throw new RuntimeException("O estoque inicial não pode ser negativo.");
        }
        return repository.save(product);
    }

    public Product updateStock(Long id, Integer quantity) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        int newStock = product.getQty_stock() + quantity;

        if (newStock < 0) {
            throw new RuntimeException("Estoque insuficiente para esta operação.");
        }

        product.setQty_stock(newStock);
        return repository.save(product); 
    }
    

    public void deleteProduct(Long id) {
    repository.deleteById(id);
}

    public Optional<Product> findById(Long id) {
    return repository.findById(id);
}
    public List<Product> searchByName(String name) {
    return repository.findByNameContainingIgnoreCase(name);
}
    // REMOVA COMPLETAMENTE o método que tinha o "throw new UnsupportedOperationException"
}