package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface IGroupUserController {
  ResponseEntity<@NonNull ResponseDto<AddGroupUserResponseDto>> addGroupUser (Long groupId, Long userId, AddGroupUserRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<RemoveGroupUserResponseDto>> removeGroupUser (Long groupId, Long userId, RemoveGroupUserRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<FetchGroupUsersResponseDto>> fetchGroupUsers (Long groupId);
  ResponseEntity<@NonNull ResponseDto<AddGroupUsersResponseDto>> addGroupUsers (Long groupId, AddGroupUsersRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<RemoveGroupUsersResponseDto>> removeGroupUsers (Long groupId, RemoveGroupUsersRequestDto requestDto);
}
