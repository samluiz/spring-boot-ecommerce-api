package com.saurs.ecommerce.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.saurs.ecommerce.entities.Order;
import com.saurs.ecommerce.entities.User;
import com.saurs.ecommerce.entities.enums.OrderStatus;
import com.saurs.ecommerce.repositories.OrderRepository;
import com.saurs.ecommerce.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public void run(String... args) throws Exception {
    
    User u1 = new User(null, "Samuel Luiz", "samuel@mail.com", "99999999999", "mypassword");
    User u2 = new User(null, "Luiza França", "luiza@mail.com", "77977777777", "herpassword");
    
    userRepository.saveAll(Arrays.asList(u1, u2));

    Order o1 = new Order(null, Instant.parse("2023-01-24T13:03:44Z"), OrderStatus.SHIPPED, u1);
    Order o2 = new Order(null, Instant.parse("2023-01-12T16:12:33Z"), OrderStatus.PAID, u2);
    Order o3 = new Order(null, Instant.parse("2023-01-25T05:43:20Z"), OrderStatus.WAITING_PAYMENT, u1);

    orderRepository.saveAll(Arrays.asList(o1, o2, o3));
  }
}
