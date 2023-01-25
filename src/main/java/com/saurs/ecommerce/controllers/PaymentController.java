package com.saurs.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurs.ecommerce.entities.Payment;
import com.saurs.ecommerce.services.PaymentService;

@RestController
@RequestMapping(value = "/Payments")
public class PaymentController {

  @Autowired
  private PaymentService service;

  @GetMapping
  public ResponseEntity<List<Payment>> findAllPayments() {
    List<Payment> payments = service.findAll();
    return ResponseEntity.ok().body(payments);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Payment> findPaymentById(@PathVariable Long id) {
    Payment payment = service.findById(id);
    if (payment == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(payment);
  }
}
