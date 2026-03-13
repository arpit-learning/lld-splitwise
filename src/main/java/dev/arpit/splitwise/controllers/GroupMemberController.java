package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.exceptions.*;
import dev.arpit.splitwise.mappers.GroupMemberDTOs;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.services.IGroupMemberService;
import dev.arpit.splitwise.services.IGroupService;
import dev.arpit.splitwise.services.IUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupMemberController implements IGroupMemberController {
  @Autowired
  private IGroupMemberService iGroupMemberService;
  @Autowired
  private IGroupService iGroupService;
  @Autowired
  private IUserService iUserService;

  @Override
  @PostMapping(Endpoints.v1groupsByIdMembersById)
  public ResponseEntity<@NonNull ResponseDto<AddGroupMemberResponseDto>> addGroupMember(@PathVariable Long groupId, @PathVariable Long memberId, @RequestBody AddGroupMemberRequestDto requestDto){
    ResponseDto<AddGroupMemberResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForAddGroupMember(groupId, memberId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User member = iUserService.findById(memberId);
      User admin = iUserService.findById(adminId);
      GroupMember groupMember = iGroupMemberService.addGroupMember(group, member, admin);
      responseDto.setData(GroupMemberDTOs.getAddGroupMemberResponseDto(groupMember));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "User with id " + groupMember.getMember().getId() + " is added to the group with id " + groupMember.getGroup().getId(),
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
  @DeleteMapping(Endpoints.v1groupsByIdMembersById)
  public ResponseEntity<@NonNull ResponseDto<RemoveGroupMemberResponseDto>> removeGroupMember(@PathVariable Long groupId, @PathVariable Long memberId, @RequestBody RemoveGroupMemberRequestDto requestDto) {
    ResponseDto<RemoveGroupMemberResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForRemoveGroupMember(groupId, memberId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User member = iUserService.findById(memberId);
      User admin = iUserService.findById(adminId);
      GroupMember groupMember = iGroupMemberService.removeGroupMember(group, member, admin);
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Removed user with id " + groupMember.getMember().getId() + " from the group with id " + groupMember.getGroup().getId(),
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
  @GetMapping(Endpoints.v1groupsByIdMembers)
  public ResponseEntity<@NonNull ResponseDto<FetchGroupMembersResponseDto>> fetchGroupMembers(@PathVariable Long groupId) {
    ResponseDto<FetchGroupMembersResponseDto> responseDto = new ResponseDto<>();
    try {
      doValidationsForFetchGroupMembers(groupId);
      Group group = iGroupService.findById(groupId);
      List<GroupMember> groupMembers = iGroupMemberService.findAllByGroup(group);
      responseDto.setData(GroupMemberDTOs.getFetchGroupMembersResponseDto(group, groupMembers));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Fetched members of group with id " + groupId,
          "Members fetched successfully",
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
  @PostMapping(Endpoints.v1groupsByIdMembers)
  public ResponseEntity<@NonNull ResponseDto<AddGroupMembersResponseDto>> addGroupMembers (@PathVariable Long groupId, @RequestBody AddGroupMembersRequestDto requestDto) {
    ResponseDto<AddGroupMembersResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForAddGroupMembers(groupId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User admin = iUserService.findById(adminId);
      List<User> members = iUserService.findAllById(requestDto.getMemberIds());
      List<GroupMember> groupMembers = iGroupMemberService.addGroupMembers(group, members, admin);
      responseDto.setData(GroupMemberDTOs.getAddGroupMembersResponseDto(groupMembers));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Added members to the group with id " + groupId,
          "Members added successfully",
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
  @DeleteMapping(Endpoints.v1groupsByIdMembers)
  public ResponseEntity<@NonNull ResponseDto<RemoveGroupMembersResponseDto>> removeGroupMembers (Long groupId, RemoveGroupMembersRequestDto requestDto) {
    ResponseDto<RemoveGroupMembersResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForRemoveGroupMembers(groupId, requestDto);
      long adminId = requestDto.getAdminId();
      Group group = iGroupService.findById(groupId);
      User admin = iUserService.findById(adminId);
      List<User> members = iUserService.findAllById(requestDto.getMemberIds());
      iGroupMemberService.removeGroupMembers(group, members, admin);
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Removed members from the group with id " + groupId,
          "Members removed successfully",
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

  private void doValidationsForAddGroupMember (Long groupId, Long memberId, AddGroupMemberRequestDto requestDto) throws InvalidGroupIdException, InvalidUserIdException, InvalidAddGroupMemberRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(memberId == null || memberId == 0L) {
      throw new InvalidUserIdException(ResponseCode.SW_ERR_400, "Member id can't be null or 0", "Please share the correct member id in the path");
    }
    if(requestDto == null) {
      throw new InvalidAddGroupMemberRequestDtoException(ResponseCode.SW_ERR_400, "Add Member Request DTO can't be null", "Please share the correct add member request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidAddGroupMemberRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
  }

  private void doValidationsForRemoveGroupMember (Long groupId, Long memberId, RemoveGroupMemberRequestDto requestDto) throws InvalidGroupIdException, InvalidUserIdException, InvalidRemoveGroupMemberRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(memberId == null || memberId == 0L) {
      throw new InvalidUserIdException(ResponseCode.SW_ERR_400, "Member id can't be null or 0", "Please share the correct member id in the path");
    }
    if(requestDto == null) {
      throw new InvalidRemoveGroupMemberRequestDtoException(ResponseCode.SW_ERR_400, "Add Member Request DTO can't be null", "Please share the correct add member request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidRemoveGroupMemberRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
  }

  private void doValidationsForFetchGroupMembers (Long groupId) throws InvalidGroupIdException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
  }

  private void doValidationsForAddGroupMembers (Long groupId, AddGroupMembersRequestDto requestDto) throws InvalidGroupIdException, InvalidAddGroupMembersRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(requestDto == null) {
      throw new InvalidAddGroupMembersRequestDtoException(ResponseCode.SW_ERR_400, "Add Members Request DTO can't be null", "Please share the correct add members request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidAddGroupMembersRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
    if(requestDto.getMemberIds() == null || requestDto.getMemberIds().isEmpty()) {
      throw new InvalidAddGroupMembersRequestDtoException(ResponseCode.SW_ERR_400, "Member ids can't be null or empty", "Please share the correct member ids in the request payload");
    }
  }

  private void doValidationsForRemoveGroupMembers (Long groupId, RemoveGroupMembersRequestDto requestDto) throws InvalidGroupIdException, InvalidRemoveGroupMembersRequestDtoException {
    if(groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(ResponseCode.SW_ERR_400, "Group id can't be null or 0", "Please share the correct group id in the path");
    }
    if(requestDto == null) {
      throw new InvalidRemoveGroupMembersRequestDtoException(ResponseCode.SW_ERR_400, "Remove Members Request DTO can't be null", "Please share the correct remove members request payload");
    }
    if(requestDto.getAdminId() == null || requestDto.getAdminId() == 0L) {
      throw new InvalidRemoveGroupMembersRequestDtoException(ResponseCode.SW_ERR_400, "Admin id can't be null or 0", "Please share the correct admin id in the request payload");
    }
    if(requestDto.getMemberIds() == null || requestDto.getMemberIds().isEmpty()) {
      throw new InvalidRemoveGroupMembersRequestDtoException(ResponseCode.SW_ERR_400, "Member ids can't be null or empty", "Please share the correct member ids in the request payload");
    }
  }
}
