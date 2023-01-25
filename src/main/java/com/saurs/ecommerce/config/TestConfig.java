package com.saurs.ecommerce.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.saurs.ecommerce.entities.User;
import com.saurs.ecommerce.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    
    User u1 = new User(null, "Samuel Luiz", "samuel@mail.com", "99999999999", "mypassword");
    User u2 = new User(null, "Luiza França", "luiza@mail.com", "77977777777", "herpassword");
    
    userRepository.saveAll(Arrays.asList(u1, u2));
  }
}
