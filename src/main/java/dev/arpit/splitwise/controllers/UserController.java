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

@RestController
public class UserController implements IUserController {
  @Autowired
  private IUserService iUserService;

  @PostMapping(Endpoints.v1Signup)
  public ResponseEntity<@NonNull ResponseDto<SignupUserResponseDto>> signupUser (@RequestBody SignupUserRequestDto requestDto) {
    ResponseDto<SignupUserResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForSignupUser(requestDto);
      User user = UserDTOs.getUser(requestDto);
      User signedupUser = iUserService.signupUser(user);
      responseDto.setData(UserDTOs.getSignupUserResponseDto(signedupUser));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "User with id " + signedupUser.getId() + " signed up successfully.",
          "User signed up successfully.",
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

  private void doValidationsForSignupUser(SignupUserRequestDto requestDto) throws InvalidSignupUserRequestDtoException {
    if(requestDto == null) {
      throw new InvalidSignupUserRequestDtoException(ResponseCode.SW_ERR_400, "Create User Request DTO can't be null", "Please share the correct create user request payload");
    }
    if(requestDto.getEmail() == null || requestDto.getEmail().isEmpty()) {
      throw new InvalidSignupUserRequestDtoException(ResponseCode.SW_ERR_400, "User email can't be null or empty", "Please share the correct create user request payload");
    }
    if(requestDto.getPassword() == null || requestDto.getPassword().isEmpty()) {
      throw new InvalidSignupUserRequestDtoException(ResponseCode.SW_ERR_400, "User password can't be null or empty", "Please share the correct create user request payload");
    }
  }

  @PutMapping(Endpoints.v1usersById)
  public ResponseEntity<@NonNull ResponseDto<UpdateUserResponseDto>> updateUser (@PathVariable Long userId, @RequestBody UpdateUserRequestDto requestDto) {
    ResponseDto<UpdateUserResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForUpdateUser(requestDto);
      User user = UserDTOs.getUser(requestDto);
      User updatedUser = iUserService.updateUser(userId, user);
      responseDto.setData(UserDTOs.getUpdateUserResponseDto(updatedUser));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "User with id " + userId + " updated successfully.",
          "User updated successfully.",
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

  private void doValidationsForUpdateUser(UpdateUserRequestDto requestDto) throws InvalidUpdateUserRequestDtoException {
    if(requestDto == null) {
      throw new InvalidUpdateUserRequestDtoException(ResponseCode.SW_ERR_400, "Create User Request DTO can't be null", "Please share the correct create user request payload");
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