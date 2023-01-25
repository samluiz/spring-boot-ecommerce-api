package com.saurs.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurs.ecommerce.entities.Product;
import com.saurs.ecommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
  
  @Autowired
  private ProductService service;

  @GetMapping
  public ResponseEntity<List<Product>> findAllProducts() {
    List<Product> products = service.findAll();
    return ResponseEntity.ok().body(products);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable Long id) {
    Product product = service.findById(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(product);
  }
}
