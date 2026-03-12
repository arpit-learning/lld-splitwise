package dev.arpit.splitwise.dtos;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
  private String name;
  private String email;
  private String mobile;
}
