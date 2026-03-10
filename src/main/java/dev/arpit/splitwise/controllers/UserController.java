package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.exceptions.BaseException;
import dev.arpit.splitwise.exceptions.InvalidCreateUserRequestDtoException;
import dev.arpit.splitwise.exceptions.InvalidDeleteUserRequestDtoException;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.mappers.UserDTOs;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.services.IUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
  @Autowired
  private IUserService iUserService;

  @PostMapping(Endpoints.v1users)
  ResponseEntity<@NonNull ResponseDto<CreateUserResponseDto>> createUser (@RequestBody CreateUserRequestDto requestDto) {
    ResponseDto<CreateUserResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForCreateUser(requestDto);
      User user = UserDTOs.getUser(requestDto);
      User createdUser = iUserService.createUser(user);
      responseDto.setData(UserDTOs.getCreateUserResponseDto(createdUser));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "User with id " + user.getId() + " created successfully.",
          "User created successfully.",
          null,
          null
      ));

      return ResponseEntity.ok(responseDto);
    } catch(BaseException e) {
      responseDto.setMeta(new MetaDataDto(
          e.getCode(),
          e.getMessage(),
          e.getDisplayMessage(),
          null,
          null
      ));

      ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
      return bodyBuilder.body(responseDto);
    }
  }

  private void doValidationsForCreateUser(CreateUserRequestDto requestDto) throws InvalidCreateUserRequestDtoException {
    if(requestDto == null) {
      throw new InvalidCreateUserRequestDtoException(ResponseCode.SW_ERR_400, "Create User Request DTO can't be null", "Please share the correct create user request payload");
    }
    if(requestDto.getName() == null || requestDto.getName().isEmpty()) {
      throw new InvalidCreateUserRequestDtoException(ResponseCode.SW_ERR_400, "User name can't be null or empty", "Please share the correct create user request payload");
    }
    if(requestDto.getEmail() == null || requestDto.getEmail().isEmpty()) {
      throw new InvalidCreateUserRequestDtoException(ResponseCode.SW_ERR_400, "User email can't be null or empty", "Please share the correct create user request payload");
    }
    if(requestDto.getMobile() == null || requestDto.getMobile().isEmpty()) {
      throw new InvalidCreateUserRequestDtoException(ResponseCode.SW_ERR_400, "User mobile can't be null or empty", "Please share the correct create user request payload");
    }
    if(requestDto.getPassword() == null || requestDto.getPassword().isEmpty()) {
      throw new InvalidCreateUserRequestDtoException(ResponseCode.SW_ERR_400, "User password can't be null or empty", "Please share the correct create user request payload");
    }
  }

  @DeleteMapping(Endpoints.v1usersById)
  public ResponseEntity<@NonNull ResponseDto<DeleteUserResponseDto>> deleteUser (@PathVariable Long userId, @RequestBody DeleteUserRequestDto requestDto) {
    ResponseDto<DeleteUserResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForDeleteUser(userId, requestDto);
      iUserService.deleteUser(userId);
      responseDto.setData(new DeleteUserResponseDto("User with id " + userId + " deleted successfully."));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "User with id " + userId + " deleted successfully.",
          "Your user deleted successfully.",
          null,
          null
      ));
      return ResponseEntity.ok(responseDto);
    } catch (BaseException e) {
      responseDto.setMeta(new MetaDataDto(
          e.getCode(),
          e.getMessage(),
          e.getDisplayMessage(),
          null,
          null
      ));
      ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
      return bodyBuilder.body(responseDto);
    }
  }

  private void doValidationsForDeleteUser(Long userId, DeleteUserRequestDto requestDto) throws InvalidUserIdException, InvalidDeleteUserRequestDtoException {
    if(userId == null || userId == 0L) {
      throw new InvalidUserIdException(ResponseCode.SW_ERR_400, "User id can't be null or 0", "Please share the correct delete user request payload");
    }
    if(requestDto == null) {
      throw new InvalidDeleteUserRequestDtoException(ResponseCode.SW_ERR_400, "Delete User Request DTO can't be null", "Please share the correct delete user request payload");
    }
  }
}