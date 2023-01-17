package com.example.waa_first_demo.service.product;

import com.example.waa_first_demo.domain.Product;

import java.util.List;


public interface ProductService {

    public List<Product> findAll();

    public Product findById(int id);

    public Product save(Product p);

    public Product delete(int id);

    public Product update(int id, Product p);

}
