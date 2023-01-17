package com.example.waa_first_demo.repo.product;

import com.example.waa_first_demo.domain.Product;
import com.example.waa_first_demo.domain.Review;

import java.util.List;

public interface ProductRepo {

    List<Product> findAll();

    Product findById(int id);

    Product save(Product p);

    Product delete(int id);

    Product update(int id, Product p);

    Review findReviewByProductId(int pId, int reviewId);

    List<Product>findAllPriceGreaterThan(int price);
}
