package dev.arpit.splitwise.dtos;

import lombok.Data;

@Data
public class SignupUserRequestDto {
  private String email;
  private String password;
}
