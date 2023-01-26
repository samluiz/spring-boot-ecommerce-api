package com.saurs.ecommerce.dtos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.saurs.ecommerce.dtos.orderItem.OrderItemGetDto;
import com.saurs.ecommerce.dtos.orderItem.OrderItemPostDto;
import com.saurs.ecommerce.entities.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

  @Mapping(target = "id", ignore = true)
  OrderItemGetDto toOrderItemGetDto(OrderItem orderItem);

  @Mapping(target = "id", ignore = true)
  OrderItemPostDto toOrderItemPostDto(OrderItem orderItem);

  @Mapping(target = "product", ignore = true)
  @Mapping(target = "order", ignore = true)
  OrderItem OrderItemPostDtoToOrderItem(OrderItemPostDto dto);

  List<OrderItemGetDto> toOrderItemGetDtoList(List<OrderItem> orderItems);
}
