package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuggestedTransactionDto {
  private UserResponseDto paidBy;
  private UserResponseDto paidTo;
  private GroupResponseDto group;
  private double amount;
}
