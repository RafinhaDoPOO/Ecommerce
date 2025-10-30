package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.Product;

public interface ProductRepository extends JpaRepository <Product,Long> {
    Product findByName(String name);
}

    
