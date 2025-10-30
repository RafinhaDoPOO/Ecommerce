package com.example.ecommerce.controller;

import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProductController {

    private final ProductService productService;
    private static final String UPLOAD_DIR = "uploads/";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Exibe o formulário para adicionar um novo produto.
     *
     * @return Nome da view do formulário.
     */
    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddProductForm() {
        return "add-product"; // Nome do arquivo HTML sem a extensão
    }

    /**
     * Obtém todos os produtos.
     *
     * @return Lista de produtos.
     */
    @GetMapping
    @ResponseBody
    public List<Product> getAllProduct() { // Mantido o nome original
        return productService.getAllProducts();
    }

    /**
     * Cria um novo produto. Acesso restrito a administradores.
     *
     * @param product Produto a ser criado.
     * @return Produto criado.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(createdProduct);
    }

    /**
     * Processa o envio do formulário para adicionar um novo produto com imagem.
     *
     * @param name        Nome do produto.
     * @param description Descrição do produto.
     * @param price       Preço do produto.
     * @param image       Arquivo de imagem do produto.
     * @return Resposta com o produto criado.
     */
    @PostMapping("/add-with-image")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Product> addProductWithImage(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image) {

        try {
            // Cria o diretório de upload se não existir
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Salva o arquivo no diretório de upload
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath);

            // Cria o produto com os dados do formulário
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setImagePath(filePath.toString());

            // Salva o produto no banco de dados
            Product createdProduct = productService.createProduct(product);

            return ResponseEntity.status(201).body(createdProduct); // Status 201 para criado
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Atualiza um produto existente. Acesso restrito a administradores.
     *
     * @param id      ID do produto a ser atualizado.
     * @param product Dados do produto a ser atualizado.
     * @return Produto atualizado.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Deleta um produto. Acesso restrito a administradores.
     *
     * @param id ID do produto a ser deletado.
     * @return Resposta sem conteúdo (status 204).
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // Retorno sem conteúdo (status 204)
    }
}