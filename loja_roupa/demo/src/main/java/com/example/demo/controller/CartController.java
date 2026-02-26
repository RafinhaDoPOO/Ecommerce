package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        List<Product> cart = getCartFromSession(session);
        model.addAttribute("cart", cart);
        model.addAttribute("total", calculateTotal(cart));
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        List<Product> cart = getCartFromSession(session);
        productService.findById(id).ifPresent(cart::add);
        session.setAttribute("cart", cart);
        return "redirect:/products/list";
    }

    @GetMapping("/checkout")
    public String showCheckout(HttpSession session, Model model) {
        List<Product> cart = getCartFromSession(session);
        if (cart.isEmpty()) {
            return "redirect:/products/list";
        }
        model.addAttribute("cart", cart);
        model.addAttribute("total", calculateTotal(cart));
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(HttpSession session, RedirectAttributes ra) {
        session.removeAttribute("cart");
        ra.addFlashAttribute("success", "Compra finalizada com sucesso! 🚀");
        return "redirect:/products/list";
    }

    @GetMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/products/list";
    }

    // Métodos Privados de Suporte
    private List<Product> getCartFromSession(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private double calculateTotal(List<Product> cart) {
        return cart.stream()
                   .filter(p -> p.getPrice() != null)
                   .mapToDouble(Product::getPrice)
                   .sum();
    }
}