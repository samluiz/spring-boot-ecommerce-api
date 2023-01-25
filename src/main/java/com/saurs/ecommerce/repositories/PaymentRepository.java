package com.saurs.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurs.ecommerce.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  
}
