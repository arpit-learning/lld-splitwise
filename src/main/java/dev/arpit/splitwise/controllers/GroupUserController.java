package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.exceptions.*;
import dev.arpit.splitwise.mappers.GroupUserDTOs;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupUser;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.services.IGroupUserService;
import dev.arpit.splitwise.services.IGroupService;
import dev.arpit.splitwise.services.IUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupUserController implements IGroupUserController {
  @Autowired
  private IGroupUserService iGroupUserService;
  @Autowired
  private IGroupService iGroupService;
  @Autowired
  private IUserService iUserService;

  @Override
  @PostMapping(Endpoints.v1groupsByIdUsersById)
  public ResponseEntity<@NonNull ResponseDto<AddGroupUserResponseDto>> addGroupUser (@PathVariable Long groupId, @PathVariable Long userId, @RequestBody AddGroupUserRequestDto requestDto){
    ResponseDto<AddGroupUserResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForAddGroupUser(groupId, userId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User user = iUserService.findById(userId);
      User admin = iUserService.findById(adminId);
      GroupUser groupUser = iGroupUserService.addGroupUser(group, user, admin);
      responseDto.setData(GroupUserDTOs.getAddGroupUserResponseDto(groupUser));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "User with id " + groupUser.getUser().getId() + " is added to the group with id " + groupUser.getGroup().getId(),
          "User added successfully",
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

  @Override
  @DeleteMapping(Endpoints.v1groupsByIdUsersById)
  public ResponseEntity<@NonNull ResponseDto<RemoveGroupUserResponseDto>> removeGroupUser (@PathVariable Long groupId, @PathVariable Long userId, @RequestBody RemoveGroupUserRequestDto requestDto) {
    ResponseDto<RemoveGroupUserResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForRemoveGroupUser(groupId, userId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User user = iUserService.findById(userId);
      User admin = iUserService.findById(adminId);
      GroupUser groupUser = iGroupUserService.removeGroupUser(group, user, admin);
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Removed user with id " + groupUser.getUser().getId() + " from the group with id " + groupUser.getGroup().getId(),
          "User removed successfully",
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

  @Override
  @GetMapping(Endpoints.v1groupsByIdUsers)
  public ResponseEntity<@NonNull ResponseDto<FetchGroupUsersResponseDto>> fetchGroupUsers (@PathVariable Long groupId) {
    ResponseDto<FetchGroupUsersResponseDto> responseDto = new ResponseDto<>();
    try {
      doValidationsForFetchGroupUsers(groupId);
      Group group = iGroupService.findById(groupId);
      List<GroupUser> groupUsers = iGroupUserService.findAllByGroup(group);
      responseDto.setData(GroupUserDTOs.getFetchGroupUsersResponseDto(group, groupUsers));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Fetched users of group with id " + groupId,
          "users fetched successfully",
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

  @Override
  @PostMapping(Endpoints.v1groupsByIdUsers)
  public ResponseEntity<@NonNull ResponseDto<AddGroupUsersResponseDto>> addGroupUsers (@PathVariable Long groupId, @RequestBody AddGroupUsersRequestDto requestDto) {
    ResponseDto<AddGroupUsersResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForAddGroupUsers(groupId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User admin = iUserService.findById(adminId);
      List<User> users = iUserService.findAllById(requestDto.getUserIds());
      List<GroupUser> groupUsers = iGroupUserService.addGroupUsers(group, users, admin);
      responseDto.setData(GroupUserDTOs.getAddGroupUsersResponseDto(groupUsers));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Added users to the group with id " + groupId,
          "Users added successfully",
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

  @Override
  @DeleteMapping(Endpoints.v1groupsByIdUsers)
  public ResponseEntity<@NonNull ResponseDto<RemoveGroupUsersResponseDto>> removeGroupUsers (@PathVariable Long groupId, @RequestBody RemoveGroupUsersRequestDto requestDto) {
    ResponseDto<RemoveGroupUsersResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForRemoveGroupUsers(groupId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User admin = iUserService.findById(adminId);
      List<User> users = iUserService.findAllById(requestDto.getUserIds());
      iGroupUserService.removeGroupUsers(group, users, admin);
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Removed users from the group with id " + groupId,
          "Users removed successfully",
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

  private void doValidationsForAddGroupUser (Long groupId, Long userId, AddGroupUserRequestDto requestDto) throws InvalidGroupIdException, InvalidUserIdException, InvalidAddGroupUserRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(userId == null || userId == 0L) {
      throw new InvalidUserIdException(ResponseCode.SW_ERR_400, "User id can't be null or 0", "Please share the correct user id in the path");
    }
    if(requestDto == null) {
      throw new InvalidAddGroupUserRequestDtoException(ResponseCode.SW_ERR_400, "Add User Request DTO can't be null", "Please share the correct add user request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidAddGroupUserRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
  }

  private void doValidationsForRemoveGroupUser (Long groupId, Long userId, RemoveGroupUserRequestDto requestDto) throws InvalidGroupIdException, InvalidUserIdException, InvalidRemoveGroupUserRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(userId == null || userId == 0L) {
      throw new InvalidUserIdException(ResponseCode.SW_ERR_400, "User id can't be null or 0", "Please share the correct user id in the path");
    }
    if(requestDto == null) {
      throw new InvalidRemoveGroupUserRequestDtoException(ResponseCode.SW_ERR_400, "Add User Request DTO can't be null", "Please share the correct add user request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidRemoveGroupUserRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
  }

  private void doValidationsForFetchGroupUsers (Long groupId) throws InvalidGroupIdException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
  }

  private void doValidationsForAddGroupUsers (Long groupId, AddGroupUsersRequestDto requestDto) throws InvalidGroupIdException, InvalidAddGroupUsersRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(requestDto == null) {
      throw new InvalidAddGroupUsersRequestDtoException(ResponseCode.SW_ERR_400, "Add Users Request DTO can't be null", "Please share the correct add users request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidAddGroupUsersRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
    if(requestDto.getUserIds() == null || requestDto.getUserIds().isEmpty()) {
      throw new InvalidAddGroupUsersRequestDtoException(ResponseCode.SW_ERR_400, "User ids can't be null or empty", "Please share the correct user ids in the request payload");
    }
  }

  private void doValidationsForRemoveGroupUsers (Long groupId, RemoveGroupUsersRequestDto requestDto) throws InvalidGroupIdException, InvalidRemoveGroupUsersRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(requestDto == null) {
      throw new InvalidRemoveGroupUsersRequestDtoException(ResponseCode.SW_ERR_400, "Remove Users Request DTO can't be null", "Please share the correct remove users request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidRemoveGroupUsersRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
    if(requestDto.getUserIds() == null || requestDto.getUserIds().isEmpty()) {
      throw new InvalidRemoveGroupUsersRequestDtoException(ResponseCode.SW_ERR_400, "User ids can't be null or empty", "Please share the correct user ids in the request payload");
    }
  }
}
