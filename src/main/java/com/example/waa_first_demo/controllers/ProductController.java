package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Product;
import com.example.waa_first_demo.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Injects @RequestBody to each method
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;


    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }


    @PostMapping
    public Product updateProduct(Product product) {
        return productService.save(product);
    }

    @DeleteMapping
    public Product deleteProduct(int id) {
        return productService.delete(id);
    }

    @PutMapping
    public Product updateProduct(int id, Product product) {
        return productService.update(id,product);
    }

}
