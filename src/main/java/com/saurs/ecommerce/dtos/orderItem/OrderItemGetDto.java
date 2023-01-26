package com.saurs.ecommerce.dtos.orderItem;

import com.saurs.ecommerce.entities.pk.OrderItemPK;

import lombok.Data;

@Data
public class OrderItemGetDto {

  private OrderItemPK id;
  private Double price;
  private Integer quantity;

}
