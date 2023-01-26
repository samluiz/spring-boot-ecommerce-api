package com.saurs.ecommerce.dtos.payment;

import java.time.Instant;

import lombok.Data;

@Data
public class PaymentGetDto {

  private Long id;
  private Instant moment;

}
