package com.saurs.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.saurs.ecommerce.entities.User;
import com.saurs.ecommerce.repositories.UserRepository;
import com.saurs.ecommerce.services.exceptions.DatabaseException;
import com.saurs.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
  
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    Optional<User> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public User add(User user) {
    return repository.save(user);
  }

  public User update(User obj, Long id) {
    try {
      User user = repository.getReferenceById(id);
      updateData(user, obj);
      return repository.save(user);
    } catch (EntityNotFoundException e) {
        throw new ResourceNotFoundException(id);
    }
  }

  private void updateData(User oldUser, User newUser) {
    oldUser.setName(newUser.getName() != null ? newUser.getName() : oldUser.getName());

    oldUser.setEmail(newUser.getEmail() != null ? newUser.getEmail() : oldUser.getEmail());

    oldUser.setPhone(newUser.getPhone() != null ? newUser.getPhone() : oldUser.getPhone());
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
        throw new DatabaseException(e.getMessage());
    }
  }

}
