package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExpenseResponseDto {
  private String description;
  private double amount;
  private List<ExpenseLedgerResponseDto> ledgers;
}
