package com.saurs.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurs.ecommerce.entities.Order;
import com.saurs.ecommerce.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
  
  @Autowired
  private OrderService service;

  @GetMapping
  public ResponseEntity<List<Order>> findAllOrders() {
    List<Order> orders = service.findAll();
    return ResponseEntity.ok().body(orders);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
    Order order = service.findById(id);
    if (order == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(order);
  }
}
