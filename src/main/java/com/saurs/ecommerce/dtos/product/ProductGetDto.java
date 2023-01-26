package com.saurs.ecommerce.dtos.product;

import java.util.Set;

import com.saurs.ecommerce.dtos.category.CategoryGetDto;

import lombok.Data;

@Data
public class ProductGetDto {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private String imgUrl;
  private Set<CategoryGetDto> categories;

}
