package com.saurs.ecommerce.dtos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.saurs.ecommerce.dtos.order.OrderGetDto;
import com.saurs.ecommerce.dtos.order.OrderPostDto;
import com.saurs.ecommerce.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  @Mapping(target = "items", ignore = true)
  @Mapping(target = "price", ignore = true)
  OrderGetDto toOrderGetDto(Order order);

  @Mapping(target = "price", ignore = true)
  @Mapping(target = "items", ignore = true)
  OrderPostDto toOrderPostDto(Order order);

  @Mapping(target = "payment", ignore = true)
  @Mapping(target = "client", ignore = true)
  @Mapping(target = "items", ignore = true)
  Order OrderPostDtoToOrder(OrderPostDto dto);

  List<OrderGetDto> toOrderGetDtoList(List<Order> orders);
}
