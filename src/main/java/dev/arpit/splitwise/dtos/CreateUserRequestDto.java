package dev.arpit.splitwise.dtos;

import lombok.Data;

@Data
public class CreateUserRequestDto {
  private String name;
  private String email;
  private String mobile;
  private String password;
}
