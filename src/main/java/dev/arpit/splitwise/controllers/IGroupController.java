package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface IGroupController {
  ResponseEntity<@NonNull ResponseDto<CreateGroupResponseDto>> createGroup(CreateGroupRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<DeleteGroupResponseDto>> deleteGroup(Long groupId, DeleteGroupRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<SettleUpGroupResponseDto>> settleUp(Long groupId, SettleUpGroupRequestDto requestDto);
}
