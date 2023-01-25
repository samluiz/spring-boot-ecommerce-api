package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.Order;
import com.saurs.ecommerce.repositories.OrderRepository;

@Service
public class OrderService {
  
  @Autowired
  private OrderRepository repository;

  public List<Order> findAll() {
    return repository.findAll();
  }

  public Order findById(Long id) {
    Optional<Order> obj = repository.findById(id);
    if (obj.isEmpty()) {
      return null;
    }
    return obj.get();
  }


}
