package com.saurs.ecommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

  @PostMapping
  public ResponseEntity<User> addUser(@RequestBody User user) {
    User addedUser = service.add(user);
    URI uri = ServletUriComponentsBuilder
    .fromCurrentRequest()
    .path("/{id}")
    .buildAndExpand(addedUser.getId())
    .toUri();

    return ResponseEntity.created(uri).body(addedUser);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    User newUser = service.update(user, id);
    return ResponseEntity.ok().body(newUser);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
