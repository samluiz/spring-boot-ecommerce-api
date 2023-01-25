package com.saurs.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurs.ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  
}
