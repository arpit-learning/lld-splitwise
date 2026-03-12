package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface IUserController {
  ResponseEntity<@NonNull ResponseDto<SignupUserResponseDto>> signupUser (SignupUserRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<LoginUserResponseDto>> loginUser (LoginUserRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<UpdateUserResponseDto>> updateUser (Long userId, UpdateUserRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<DeleteUserResponseDto>> deleteUser (Long userId, DeleteUserRequestDto requestDto);
}