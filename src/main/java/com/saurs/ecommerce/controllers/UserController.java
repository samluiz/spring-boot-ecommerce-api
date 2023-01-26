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

import com.saurs.ecommerce.dtos.mapper.UserMapper;
import com.saurs.ecommerce.dtos.user.UserGetDto;
import com.saurs.ecommerce.dtos.user.UserPostDto;
import com.saurs.ecommerce.entities.User;
import com.saurs.ecommerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
  
  @Autowired
  private UserService service;

  @Autowired
  private UserMapper mapper;

  @GetMapping
  public ResponseEntity<List<UserGetDto>> findAllUsers() {
    List<UserGetDto> users = mapper.toUserGetDtoList(service.findAll());
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserGetDto> findUserById(@PathVariable Long id) {
    UserGetDto user = mapper.toUserGetDto(service.findById(id));
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(user);
  }

  @PostMapping
  public ResponseEntity<UserPostDto> addUser(@RequestBody User user) {
    UserPostDto addedUser = mapper.toUserPostDto(service.add(user));
    URI uri = ServletUriComponentsBuilder
    .fromCurrentRequest()
    .path("/{id}")
    .buildAndExpand(addedUser.getId())
    .toUri();

    return ResponseEntity.created(uri).body(addedUser);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<UserPostDto> updateUser(@PathVariable Long id, @RequestBody User user) {
    UserPostDto newUser = mapper.toUserPostDto(service.update(user, id));
    return ResponseEntity.ok().body(newUser);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
