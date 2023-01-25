package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.Payment;
import com.saurs.ecommerce.repositories.PaymentRepository;

@Service
public class PaymentService {
  
  @Autowired
  private PaymentRepository repository;

  public List<Payment> findAll() {
    return repository.findAll();
  }

  public Payment findById(Long id) {
    Optional<Payment> obj = repository.findById(id);
    if (obj.isEmpty()) {
      return null;
    }
    return obj.get();
  }


}
