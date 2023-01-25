package com.saurs.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurs.ecommerce.entities.Category;
import com.saurs.ecommerce.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
  
  @Autowired
  private CategoryService service;

  @GetMapping
  public ResponseEntity<List<Category>> findAllCategorys() {
    List<Category> categories = service.findAll();
    return ResponseEntity.ok().body(categories);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
    Category category = service.findById(id);
    if (category == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(category);
  }
}
