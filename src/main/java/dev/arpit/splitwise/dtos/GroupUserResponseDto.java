package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupUserResponseDto {
  private String name;
  private String email;
  private String mobile;
  private String userType;
}
