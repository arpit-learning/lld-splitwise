package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateExpenseResponseDto {
  private ExpenseResponseDto expense;
}
