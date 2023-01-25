package com.saurs.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurs.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
