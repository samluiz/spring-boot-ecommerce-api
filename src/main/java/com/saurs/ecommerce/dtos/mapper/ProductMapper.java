package com.saurs.ecommerce.dtos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.saurs.ecommerce.dtos.product.ProductGetDto;
import com.saurs.ecommerce.dtos.product.ProductPostDto;
import com.saurs.ecommerce.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductGetDto toProductGetDto(Product product);

  ProductPostDto toProductPostDto(Product product);

  Product ProductPostDtoToProduct(ProductPostDto dto);

  List<ProductGetDto> toProductGetDtoList(List<Product> products);
}
