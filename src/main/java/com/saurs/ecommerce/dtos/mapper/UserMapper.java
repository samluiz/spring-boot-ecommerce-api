package com.saurs.ecommerce.dtos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.saurs.ecommerce.dtos.user.UserGetDto;
import com.saurs.ecommerce.dtos.user.UserPostDto;
import com.saurs.ecommerce.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserGetDto toUserGetDto(User user);

  UserPostDto toUserPostDto(User user);

  User userPostDtoToUser(UserPostDto dto);

  List<UserGetDto> toUserGetDtoList(List<User> users);
}
