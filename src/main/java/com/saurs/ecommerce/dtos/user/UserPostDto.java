package com.saurs.ecommerce.dtos.user;

import java.util.List;

import com.saurs.ecommerce.entities.Order;

import lombok.Data;

@Data
public class UserPostDto {

  private Long id;
  private String name;
  private String email;
  private String phone;
  private String password;
  private List<Order> orders;

}
