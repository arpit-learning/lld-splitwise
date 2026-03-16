package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.ExpenseLedgerResponseDto;
import dev.arpit.splitwise.dtos.UserLedgerPairDto;
import dev.arpit.splitwise.models.ExpenseLedger;
import dev.arpit.splitwise.models.UserIdLedgerPair;

import java.util.List;

public class ExpenseLedgerDTOs {
  public static ExpenseLedgerResponseDto getExpenseLedgerResponseDto(ExpenseLedger ledger) {
    return new ExpenseLedgerResponseDto(
        ledger.getUser().getName(),
        ledger.getAmount(),
        ledger.getExpenseType().toString()
    );
  }

  public static List<ExpenseLedgerResponseDto> getExpenseLedgersResponseDto(List<ExpenseLedger> ledgers) {
    return ledgers.stream().map(ledger -> new ExpenseLedgerResponseDto(
        ledger.getUser().getName(),
        ledger.getAmount(),
        ledger.getExpenseType().toString()
    )).toList();
  }

  public static List<UserIdLedgerPair> getUserIdLegerPairList(List<UserLedgerPairDto> userLedgerPairDTOs) {
    return userLedgerPairDTOs.stream().map(e -> new UserIdLedgerPair(
        e.getUserId(),
        e.getAmount()
    )).toList();
  }
}
