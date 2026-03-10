package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
  private long id;
  private String name;
  private String email;
  private String mobile;
}
