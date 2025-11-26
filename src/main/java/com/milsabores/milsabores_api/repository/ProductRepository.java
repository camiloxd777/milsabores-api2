package com.milsabores.milsabores_api.repository;

import com.milsabores.milsabores_api.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActivoTrue();
}
