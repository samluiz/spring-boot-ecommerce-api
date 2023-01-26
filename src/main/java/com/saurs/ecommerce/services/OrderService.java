package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.Order;
import com.saurs.ecommerce.entities.OrderItem;
import com.saurs.ecommerce.repositories.OrderItemRepository;
import com.saurs.ecommerce.repositories.OrderRepository;
import com.saurs.ecommerce.services.exceptions.DatabaseException;
import com.saurs.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
  
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemService orderItemService;

  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  public Order findById(Long id) {
    Optional<Order> obj = orderRepository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  public Order addItemInOrder(Long orderId, OrderItem orderItem) {
    Optional<Order> order = orderRepository.findById(orderId);

    for (OrderItem oi : order.orElseThrow(() -> new ResourceNotFoundException(orderId)).getItems()) {
      if (oi.getOrder().getId().equals(orderItem.getOrder().getId())) {
        oi.setQuantity((oi.getQuantity() + 1));
      }
    }

    OrderItem newItem = orderItemService.addItem(orderItem);
    order.get().getItems().add(newItem);

    return order.get();
  }

  // public Order removeItemFromOrder(Long orderId, Long orderItemId) {
    
  // }
  
  public void delete(Long id) {
    try {
      orderRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
        throw new DatabaseException(e.getMessage());
    }
  }


}
