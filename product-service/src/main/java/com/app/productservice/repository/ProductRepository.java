package com.app.productservice.repository;

import com.app.productservice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByCategoryName(String categoryName);
}
