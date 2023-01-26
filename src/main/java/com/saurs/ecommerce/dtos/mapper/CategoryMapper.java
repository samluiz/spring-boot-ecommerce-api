package com.saurs.ecommerce.dtos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.saurs.ecommerce.dtos.category.CategoryGetDto;
import com.saurs.ecommerce.dtos.category.CategoryPostDto;
import com.saurs.ecommerce.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  CategoryGetDto toCategoryGetDto(Category category);

  CategoryPostDto toCategoryPostDto(Category category);

  @Mapping(target = "products", ignore = true)
  Category CategoryPostDtoToCategory(CategoryPostDto dto);

  List<CategoryGetDto> toCategoryGetDtoList(List<Category> categories);
}
