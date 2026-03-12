package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.models.User;

public class UserDTOs {
  public static UserResponseDto getUserResponseDto(User user) {
    return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getMobile());
  }

  public static UpdateUserResponseDto getUpdateUserResponseDto(User user) {
    return new UpdateUserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getMobile());
  }

  public static User getUser(UpdateUserRequestDto requestDto) {
    return new User(requestDto.getName(), requestDto.getEmail(), requestDto.getMobile(), null);
  }

  public static User getUser(SignupUserRequestDto requestDto) {
    return new User(null, requestDto.getEmail(), null, requestDto.getPassword());
  }

  public static SignupUserResponseDto getSignupUserResponseDto(User user) {
    return new SignupUserResponseDto(user.getId(), user.getEmail());
  }
}
