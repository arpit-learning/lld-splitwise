package dev.arpit.splitwise.models;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sw_user")
public class User extends BaseModel {
  private String name;
  private String email;
  private String mobile;
  private String password;
}
