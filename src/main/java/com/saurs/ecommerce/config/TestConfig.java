package com.saurs.ecommerce.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.saurs.ecommerce.entities.Category;
import com.saurs.ecommerce.entities.Order;
import com.saurs.ecommerce.entities.OrderItem;
import com.saurs.ecommerce.entities.Payment;
import com.saurs.ecommerce.entities.Product;
import com.saurs.ecommerce.entities.User;
import com.saurs.ecommerce.entities.enums.OrderStatus;
import com.saurs.ecommerce.repositories.CategoryRepository;
import com.saurs.ecommerce.repositories.OrderItemRepository;
import com.saurs.ecommerce.repositories.OrderRepository;
import com.saurs.ecommerce.repositories.PaymentRepository;
import com.saurs.ecommerce.repositories.ProductRepository;
import com.saurs.ecommerce.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private PaymentRepository paymentRepository;

  @Override
  public void run(String... args) throws Exception {
    
    User u1 = new User(null, "Samuel Luiz", "samuel@mail.com", "99999999999", "mypassword");
    User u2 = new User(null, "Luiza França", "luiza@mail.com", "77977777777", "herpassword");
    
    userRepository.saveAll(Arrays.asList(u1, u2));

    Order o1 = new Order(null, Instant.parse("2023-01-24T13:03:44Z"), OrderStatus.WAITING_PAYMENT, u1);
    Order o2 = new Order(null, Instant.parse("2023-01-12T16:12:33Z"), OrderStatus.PAID, u2);
    Order o3 = new Order(null, Instant.parse("2023-01-25T05:43:20Z"), OrderStatus.WAITING_PAYMENT, u1);

    orderRepository.saveAll(Arrays.asList(o1, o2, o3));

    Category c1 = new Category(null, "Electronics");
    Category c2 = new Category(null, "Books");
    Category c3 = new Category(null, "House");

    categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

    Product p1 = new Product(null, "Computer", "Computer that you can play cool games", 4500.0, "www.img.com/1");
    Product p2 = new Product(null, "TV", "TV that you can watch cool movies", 2500.0, "www.img.com/2");
    Product p3 = new Product(null, "Harry Potter", "Book that you can read cool stories", 45.0, "www.img.com/3");

    productRepository.saveAll(Arrays.asList(p1, p2, p3));

    p1.getCategories().add(c1);
    p2.getCategories().add(c1);
    p2.getCategories().add(c3);
    p3.getCategories().add(c2);

    productRepository.saveAll(Arrays.asList(p1, p2, p3));

    OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
    OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
    OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
    OrderItem oi4 = new OrderItem(o3, p2, 2, p2.getPrice());

    orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

    Payment pay1 = new Payment(null, Instant.parse("2023-01-25T12:01:32Z"), o1);
    Payment pay2 = new Payment(null, Instant.parse("2023-01-17T12:01:32Z"), o3);

    paymentRepository.saveAll(Arrays.asList(pay1, pay2));
  }
}
