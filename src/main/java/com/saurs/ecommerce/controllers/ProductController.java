package com.saurs.ecommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saurs.ecommerce.dtos.mapper.ProductMapper;
import com.saurs.ecommerce.dtos.product.ProductGetDto;
import com.saurs.ecommerce.dtos.product.ProductPostDto;
import com.saurs.ecommerce.entities.Product;
import com.saurs.ecommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
  
  @Autowired
  private ProductService service;

  @Autowired
  private ProductMapper mapper;

  @GetMapping
  public ResponseEntity<List<ProductGetDto>> findAllProducts() {
    List<ProductGetDto> products = mapper.toProductGetDtoList(service.findAll());
    return ResponseEntity.ok().body(products);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProductGetDto> findProductById(@PathVariable Long id) {
    ProductGetDto product = mapper.toProductGetDto(service.findById(id));
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(product);
  }

  @PostMapping
  public ResponseEntity<ProductPostDto> addProduct(@RequestBody Product product) {
    ProductPostDto addedProduct = mapper.toProductPostDto(service.add(product));
    URI uri = ServletUriComponentsBuilder
    .fromCurrentRequest()
    .path("/{id}")
    .buildAndExpand(addedProduct.getId())
    .toUri();

    return ResponseEntity.created(uri).body(addedProduct);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ProductPostDto> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    ProductPostDto newProduct = mapper.toProductPostDto(service.update(product, id));
    return ResponseEntity.ok().body(newProduct);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
