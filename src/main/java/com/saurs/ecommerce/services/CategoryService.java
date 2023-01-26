package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.Category;
import com.saurs.ecommerce.repositories.CategoryRepository;
import com.saurs.ecommerce.services.exceptions.DatabaseException;
import com.saurs.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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

  public Category add(Category category) {
    return repository.save(category);
  }

  public Category update(Category obj, Long id) {
    try {
      Category category = repository.getReferenceById(id);
      updateData(category, obj);
      return repository.save(category);
    } catch (EntityNotFoundException e) {
        throw new ResourceNotFoundException(id);
    }
  }

  public void updateData(Category oldCategory, Category newCategory) {
    oldCategory.setName(newCategory.getName() != null ? newCategory.getName() : oldCategory.getName());
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
        throw new DatabaseException(e.getMessage());
    }
  }

}
