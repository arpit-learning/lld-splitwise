package dev.arpit.splitwise.dtos;

import dev.arpit.splitwise.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserLedgerPairDto {
  private Long userId;
  private Double amount;
}
