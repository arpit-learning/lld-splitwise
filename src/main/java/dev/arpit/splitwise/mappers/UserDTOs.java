package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.CreateUserRequestDto;
import dev.arpit.splitwise.dtos.CreateUserResponseDto;
import dev.arpit.splitwise.dtos.UserResponseDto;
import dev.arpit.splitwise.models.User;

public class UserDTOs {
  public static UserResponseDto getUserResponseDto(User user) {
    return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getMobile());
  }

  public static CreateUserResponseDto getCreateUserResponseDto(User user) {
    return new CreateUserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getMobile());
  }

  public static User getUser(CreateUserRequestDto requestDto) {
    return new User(requestDto.getName(), requestDto.getEmail(), requestDto.getMobile(), requestDto.getPassword());
  }
}
