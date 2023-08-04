package com.example.demo.repositories;

import com.example.demo.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByProductName(String productName);
}
