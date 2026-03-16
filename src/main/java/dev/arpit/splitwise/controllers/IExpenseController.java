package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.CreateExpenseRequestDto;
import dev.arpit.splitwise.dtos.CreateExpenseResponseDto;
import dev.arpit.splitwise.dtos.FetchExpensesResponseDto;
import dev.arpit.splitwise.dtos.ResponseDto;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IExpenseController {
  ResponseEntity<@NonNull ResponseDto<CreateExpenseResponseDto>> createExpense(@PathVariable Long groupId, @RequestBody CreateExpenseRequestDto requestBody);
  ResponseEntity<@NonNull ResponseDto<FetchExpensesResponseDto>> fetchAllExpenses(@PathVariable Long groupId);
}
