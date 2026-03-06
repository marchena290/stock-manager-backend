package com.inventario.stock_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.stock_manager.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
