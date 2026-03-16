package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.exceptions.BaseException;
import dev.arpit.splitwise.exceptions.InvalidCreateExpenseRequestDtoException;
import dev.arpit.splitwise.exceptions.InvalidGroupIdException;
import dev.arpit.splitwise.mappers.ExpenseDTOs;
import dev.arpit.splitwise.mappers.ExpenseLedgerDTOs;
import dev.arpit.splitwise.models.*;
import dev.arpit.splitwise.services.IExpenseService;
import dev.arpit.splitwise.services.IGroupService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController implements IExpenseController {
  @Autowired
  private IExpenseService iExpenseService;
  @Autowired
  private IGroupService iGroupService;

  @Override
  @PostMapping(Endpoints.v1groupsByIdExpenses)
  public ResponseEntity<@NonNull ResponseDto<CreateExpenseResponseDto>> createExpense(@PathVariable Long groupId, @RequestBody CreateExpenseRequestDto requestDto) {
    ResponseDto<CreateExpenseResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForCreateExpense(groupId, requestDto);
      String description = requestDto.getDescription();
      List<UserLedgerPairDto> ledgersDto = requestDto.getLedgers();
      double amount = requestDto.getAmount();

      Group group = iGroupService.findById(groupId);
      List<UserIdLedgerPair> ledgers = ExpenseLedgerDTOs.getUserIdLegerPairList(ledgersDto);
      Expense expense = iExpenseService.createExpense(group, description, amount, ledgers);
      responseDto.setData(ExpenseDTOs.getCreateExpenseResponseDto(expense));
      responseDto.setMeta(new MetaDataDto(
          ResponseCode.SW_SEC_200,
          "Expense created successfully with id " + expense.getId(),
          "Expense created successfully",
          null,
          null
      ));

      return ResponseEntity.ok(responseDto);
    } catch (BaseException e) {
      responseDto.setMeta(new MetaDataDto(
          e.getCode(),
          e.getMessage(),
          e.getDisplayMessage(),
          null,
          null
      ));

      ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
      return bodyBuilder.body(responseDto);
    }
  }

  @Override
  @GetMapping(Endpoints.v1groupsByIdExpenses)
  public ResponseEntity<@NonNull ResponseDto<FetchExpensesResponseDto>> fetchAllExpenses (Long groupId) {
    ResponseDto<FetchExpensesResponseDto> responseDto = new ResponseDto<>();

    try {
      doValidationsForFetchExpenses(groupId);
      Group group = iGroupService.findById(groupId);
      List<Expense> expenses = iExpenseService.fetchExpenses(group);
      responseDto.setData(ExpenseDTOs.getFetchExpensesResponseDto(expenses));
      responseDto.setMeta(
          new MetaDataDto(
              ResponseCode.SW_SEC_200,
              "Fetched all the expenses for the group with group id " + groupId,
              "Fetched all the expenses",
              null,
              null
          )
      );

      return ResponseEntity.ok(responseDto);
    } catch (BaseException e) {
      responseDto.setMeta(new MetaDataDto(
          e.getCode(),
          e.getMessage(),
          e.getDisplayMessage(),
          null,
          null
      ));

      ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
      return bodyBuilder.body(responseDto);
    }
  }

  private void doValidationsForCreateExpense(Long groupId, CreateExpenseRequestDto requestDto) throws InvalidGroupIdException, InvalidCreateExpenseRequestDtoException {
    if (groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(
          ResponseCode.SW_ERR_400,
          "groupId " + groupId + " is invalid.",
          "GroupId is invalid."
      );
    }
    if (requestDto == null) {
      throw new InvalidCreateExpenseRequestDtoException(
          ResponseCode.SW_ERR_400,
          "request payload is null",
          "Invalid request payload"
      );
    }
    if (requestDto.getDescription() == null || requestDto.getDescription().isEmpty()) {
      throw new InvalidCreateExpenseRequestDtoException(
          ResponseCode.SW_ERR_400,
          "description is empty or null",
          "Please add a description to the expense."
      );
    }
    if (requestDto.getAmount() == null || requestDto.getAmount() == 0) {
      throw new InvalidCreateExpenseRequestDtoException(
          ResponseCode.SW_ERR_400,
          "amount can't be zero",
          "Please add a amount to the expense"
      );
    }
    if (requestDto.getLedgers() == null || requestDto.getLedgers().isEmpty()) {
      throw new InvalidCreateExpenseRequestDtoException(
          ResponseCode.SW_ERR_400,
          "ledgers can't be empty",
          "Please add ledgers as well"
      );
    }
  }

  private void doValidationsForFetchExpenses(Long groupId) throws InvalidGroupIdException {
    if (groupId == null || groupId == 0L) {
      throw new InvalidGroupIdException(
          ResponseCode.SW_ERR_400,
          "groupId " + groupId + " is invalid.",
          "GroupId is invalid."
      );
    }
  }
}
