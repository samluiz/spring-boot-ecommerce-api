package com.saurs.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurs.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
  
}
