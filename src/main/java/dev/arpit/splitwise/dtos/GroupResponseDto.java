package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupResponseDto {
  private long id;
  private String groupName;
  private String groupDescription;
  private UserResponseDto createdBy;
}
