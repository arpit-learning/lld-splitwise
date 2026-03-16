package dev.arpit.splitwise.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserIdLedgerPair {
  private Long userId;
  private double ledger;
}
