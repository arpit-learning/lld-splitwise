package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.CreateExpenseResponseDto;
import dev.arpit.splitwise.dtos.ExpenseResponseDto;
import dev.arpit.splitwise.dtos.FetchExpensesResponseDto;
import dev.arpit.splitwise.models.Expense;

import java.util.List;

public class ExpenseDTOs {
  public static ExpenseResponseDto getExpenseResponseDto(Expense expense) {
    return new ExpenseResponseDto(
        expense.getDescription(),
        expense.getAmount(),
        ExpenseLedgerDTOs.getExpenseLedgersResponseDto(expense.getExpenseLedgers())
    );
  }

  public static CreateExpenseResponseDto getCreateExpenseResponseDto(Expense expense) {
    return new CreateExpenseResponseDto(ExpenseDTOs.getExpenseResponseDto(expense));
  }

  public static FetchExpensesResponseDto getFetchExpensesResponseDto(List<Expense> expenses) {
    return new FetchExpensesResponseDto(
        expenses.stream().map(ExpenseDTOs::getExpenseResponseDto).toList()
    );
  }
}
