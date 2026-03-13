package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface IGroupMemberController {
  ResponseEntity<@NonNull ResponseDto<AddGroupMemberResponseDto>> addGroupMember(Long groupId, Long memberId, AddGroupMemberRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<RemoveGroupMemberResponseDto>> removeGroupMember(Long groupId, Long memberId, RemoveGroupMemberRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<FetchGroupMembersResponseDto>> fetchGroupMembers(Long groupId);
  ResponseEntity<@NonNull ResponseDto<AddGroupMembersResponseDto>> addGroupMembers(Long groupId, AddGroupMembersRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<RemoveGroupMembersResponseDto>> removeGroupMembers(Long groupId, RemoveGroupMembersRequestDto requestDto);
}
