package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.OrderItem;
import com.saurs.ecommerce.repositories.OrderItemRepository;

@Service
public class OrderItemService {
  
  @Autowired
  private OrderItemRepository repository;

  public List<OrderItem> findAll() {
    return repository.findAll();
  }

  public OrderItem findById(Long id) {
    Optional<OrderItem> obj = repository.findById(id);
    if (obj.isEmpty()) {
      return null;
    }
    return obj.get();
  }


}
