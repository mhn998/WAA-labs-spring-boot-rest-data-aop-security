package com.example.waa_first_demo.service.product;

import com.example.waa_first_demo.domain.Product;
import com.example.waa_first_demo.repo.product.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    ProductRepo productRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(int id) {
        return productRepo.findById(id);
    }

    public Product save(Product p) {
        return productRepo.save(p);
    }

    public Product delete(int id) {
        return productRepo.delete(id);
    }

    public Product update(int id, Product p) {
        return productRepo.update(id, p);
    }

}
