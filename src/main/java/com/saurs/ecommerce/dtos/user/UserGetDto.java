package com.saurs.ecommerce.dtos.user;

import lombok.Data;

@Data
public class UserGetDto {

  private Long id;
  private String name;
  private String email;
  private String phone;

}
