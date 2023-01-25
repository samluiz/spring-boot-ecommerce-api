package com.saurs.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurs.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
