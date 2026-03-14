package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.Endpoints;
import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.exceptions.*;
import dev.arpit.splitwise.mappers.GroupDTOs;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.services.IGroupService;
import dev.arpit.splitwise.services.IUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController implements IGroupController {

  @Autowired
  private IGroupService iGroupService;
  @Autowired
  private IUserService iUserService;

  @Override
  @PostMapping(Endpoints.v1groups)
  public ResponseEntity<@NonNull ResponseDto<CreateGroupResponseDto>> createGroup(@RequestBody CreateGroupRequestDto requestDto) {
    ResponseDto<CreateGroupResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForCreateGroup(requestDto);
      String name = requestDto.getName();
      String description = requestDto.getDescription();
      long userId = requestDto.getCreatorUserId();
      User admin = iUserService.findById(userId);
      Group group = iGroupService.createGroup(name, description, admin);
      responseDto.setData(
          GroupDTOs.getCreateGroupResponseDto(GroupDTOs.getGroupResponseDto(group))
      );
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Group with id " + group.getId() + " created successfully.",
          "Your group created successfully. You can add users to the group now.",
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
  @DeleteMapping(Endpoints.v1groupsById)
  public ResponseEntity<@NonNull ResponseDto<DeleteGroupResponseDto>> deleteGroup(@PathVariable Long groupId, @RequestBody DeleteGroupRequestDto requestDto) {
    ResponseDto<DeleteGroupResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForDeleteGroup(groupId, requestDto);
      long userId = requestDto.getUserId();
      User admin = iUserService.findById(userId);
      Group group = iGroupService.deleteGroup(groupId, admin);

      responseDto.setData(
          GroupDTOs.getDeleteGroupResponseDto(GroupDTOs.getGroupResponseDto(group))
      );
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Group with id + " + group.getId() + " deleted successfully.",
          "Your group deleted successfully.",
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

  private void doValidationsForCreateGroup(CreateGroupRequestDto requestDto) throws InvalidCreateGroupRequestDtoException {
    if(requestDto == null) {
      throw new InvalidCreateGroupRequestDtoException(ResponseCode.SW_ERR_400, "Create Group Request DTO can't be null", "Please share the correct create group request payload");
    }
    if(requestDto.getName() == null || requestDto.getName().isEmpty()) {
      throw new InvalidCreateGroupRequestDtoException(ResponseCode.SW_ERR_400, "Group name can't be null or empty", "Please share the correct create group request payload");
    }
    if(requestDto.getDescription() == null || requestDto.getDescription().isEmpty()) {
      throw new InvalidCreateGroupRequestDtoException(ResponseCode.SW_ERR_400, "Group description can't be null or empty", "Please share the correct create group request payload");
    }
    if(requestDto.getCreatorUserId() == null || requestDto.getCreatorUserId() == 0L) {
      throw new InvalidCreateGroupRequestDtoException(ResponseCode.SW_ERR_400, "Group creator user id can't be null or 0", "Please share the correct create group request payload");
    }
  }

  private void doValidationsForDeleteGroup(Long groupId, DeleteGroupRequestDto requestDto) throws InvalidGroupIdException, InvalidDeleteGroupRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct delete group request payload");
    }
    if(requestDto == null) {
      throw new InvalidDeleteGroupRequestDtoException(ResponseCode.SW_ERR_400, "Delete Group Request DTO can't be null", "Please share the correct delete group request payload");
    }
    if(requestDto.getUserId() == null || requestDto.getUserId() == 0L) {
      throw new InvalidDeleteGroupRequestDtoException(ResponseCode.SW_ERR_400, "User id can't be null or 0", "Please share the correct delete group request payload");
    }
  }
}
