package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddGroupUsersResponseDto {
  private GroupResponseDto group;
  private List<UserResponseDto> users;
  private UserResponseDto addedBy;
}
