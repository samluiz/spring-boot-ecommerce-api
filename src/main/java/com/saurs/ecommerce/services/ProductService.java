package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.Product;
import com.saurs.ecommerce.repositories.ProductRepository;
import com.saurs.ecommerce.services.exceptions.DatabaseException;
import com.saurs.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
  
  @Autowired
  private ProductRepository repository;

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Product findById(Long id) {
    Optional<Product> obj = repository.findById(id);
    if (obj.isEmpty()) {
      return null;
    }
    return obj.get();
  }

  public Product add(Product product) {
    return repository.save(product);
  }

  public Product update(Product obj, Long id) {
    try {
      Product product = repository.getReferenceById(id);
      updateData(product, obj);
      return repository.save(product);
    } catch (EntityNotFoundException e) {
        throw new ResourceNotFoundException(id);
    }
  }

  private void updateData(Product oldProduct, Product newProduct) {
    oldProduct.setName(newProduct.getName() != null ? newProduct.getName() : oldProduct.getName());

    oldProduct.setDescription(newProduct.getDescription() != null ? newProduct.getDescription() : oldProduct.getDescription());

    oldProduct.setPrice(newProduct.getPrice() != null ? newProduct.getPrice() : oldProduct.getPrice());

    oldProduct.setImgUrl(newProduct.getImgUrl() != null ? newProduct.getImgUrl() : oldProduct.getImgUrl());
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
