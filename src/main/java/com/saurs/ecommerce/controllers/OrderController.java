package com.saurs.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurs.ecommerce.dtos.mapper.OrderMapper;
import com.saurs.ecommerce.dtos.order.OrderGetDto;
import com.saurs.ecommerce.dtos.order.OrderPostDto;
import com.saurs.ecommerce.entities.Order;
import com.saurs.ecommerce.entities.OrderItem;
import com.saurs.ecommerce.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
  
  @Autowired
  private OrderService service;

  @Autowired
  private OrderMapper mapper;

  @GetMapping
  public ResponseEntity<List<OrderGetDto>> findAllOrders() {
    List<OrderGetDto> orders = mapper.toOrderGetDtoList(service.findAll());
    return ResponseEntity.ok().body(orders);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderGetDto> findOrderById(@PathVariable Long id) {
    OrderGetDto order = mapper.toOrderGetDto(service.findById(id));
    if (order == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(order);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<OrderPostDto> addItemInOrder(@PathVariable Long id, @RequestBody OrderItem orderItem) {
    OrderPostDto order = mapper.toOrderPostDto(service.addItemInOrder(id, orderItem));

     return ResponseEntity.ok().body(order);
  }
}
