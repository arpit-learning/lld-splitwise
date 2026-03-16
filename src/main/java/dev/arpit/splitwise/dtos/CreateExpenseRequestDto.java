package dev.arpit.splitwise.dtos;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

@Data
public class CreateExpenseRequestDto {
  private String description;
  private Double amount;
  private List<UserLedgerPairDto> ledgers;
}
