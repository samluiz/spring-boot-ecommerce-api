package com.saurs.ecommerce.dtos.order;

import java.time.Instant;
import java.util.Set;

import com.saurs.ecommerce.dtos.payment.PaymentGetDto;
import com.saurs.ecommerce.dtos.user.UserGetDto;
import com.saurs.ecommerce.dtos.orderItem.OrderItemGetDto;
import com.saurs.ecommerce.entities.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderGetDto {

  private Long id;
  private Instant moment;
  private OrderStatus orderStatus;
  private Double price;
  private UserGetDto client;
  private Set<OrderItemGetDto> items;
  private PaymentGetDto payment;

}
