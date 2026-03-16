package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseLedgerResponseDto {
  private String name;
  private double amount;
  private String type;
}
