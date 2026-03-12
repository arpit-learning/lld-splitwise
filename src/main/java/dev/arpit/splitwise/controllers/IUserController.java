package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.exceptions.*;
import dev.arpit.splitwise.mappers.UserDTOs;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.services.IUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IUserController {
  ResponseEntity<@NonNull ResponseDto<SignupUserResponseDto>> signupUser (@RequestBody SignupUserRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<UpdateUserResponseDto>> updateUser (@PathVariable Long userId, @RequestBody UpdateUserRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<DeleteUserResponseDto>> deleteUser (@PathVariable Long userId, @RequestBody DeleteUserRequestDto requestDto);
}