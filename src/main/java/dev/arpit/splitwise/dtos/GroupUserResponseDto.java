package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupUserResponseDto {
  private long id;
  private String name;
  private String email;
  private String mobile;
  private String userType;
}
