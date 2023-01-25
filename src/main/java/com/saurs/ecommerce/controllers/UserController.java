package com.saurs.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurs.ecommerce.entities.User;
import com.saurs.ecommerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
  
  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<User>> findAllUsers() {
    List<User> users = service.findAll();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findUserById(@PathVariable Long id) {
    User user = service.findById(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(user);
  }
}