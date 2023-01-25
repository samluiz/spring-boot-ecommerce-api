package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.Category;
import com.saurs.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository repository;

  public List<Category> findAll() {
    return repository.findAll();
  }

  public Category findById(Long id) {
    Optional<Category> obj = repository.findById(id);
    if (obj.isEmpty()) {
      return null;
    }
    return obj.get();
  }


}
