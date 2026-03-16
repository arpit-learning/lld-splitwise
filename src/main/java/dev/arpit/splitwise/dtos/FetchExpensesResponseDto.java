package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FetchExpensesResponseDto {
  private List<ExpenseResponseDto> expenses;
}
