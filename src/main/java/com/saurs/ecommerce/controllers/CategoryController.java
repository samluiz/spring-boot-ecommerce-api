package com.saurs.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurs.ecommerce.dtos.category.CategoryGetDto;
import com.saurs.ecommerce.dtos.category.CategoryPostDto;
import com.saurs.ecommerce.dtos.mapper.CategoryMapper;
import com.saurs.ecommerce.entities.Category;
import com.saurs.ecommerce.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
  
  @Autowired
  private CategoryService service;

  @Autowired
  private CategoryMapper mapper;

  @GetMapping
  public ResponseEntity<List<CategoryGetDto>> findAllCategories() {
    List<CategoryGetDto> categories = mapper.toCategoryGetDtoList(service.findAll());
    return ResponseEntity.ok().body(categories);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<CategoryGetDto> findCategoryById(@PathVariable Long id) {
    CategoryGetDto category = mapper.toCategoryGetDto(service.findById(id));
    if (category == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(category);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<CategoryPostDto> updateCategory(@PathVariable Long id, @RequestBody Category category) {
    CategoryPostDto newCategory = mapper.toCategoryPostDto(service.update(category, id));
    return ResponseEntity.ok().body(newCategory);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
