package com.saurs.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurs.ecommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
}
