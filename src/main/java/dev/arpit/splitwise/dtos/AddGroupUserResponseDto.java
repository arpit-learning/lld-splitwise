package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddGroupUserResponseDto {
  private GroupResponseDto group;
  private UserResponseDto user;
  private UserResponseDto addedBy;
}
