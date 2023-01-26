package com.saurs.ecommerce.dtos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.saurs.ecommerce.dtos.payment.PaymentGetDto;
import com.saurs.ecommerce.entities.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
  PaymentGetDto toPaymentGetDto(Payment payment);

  List<PaymentGetDto> toPaymentGetDtoList(List<Payment> payments);
}
