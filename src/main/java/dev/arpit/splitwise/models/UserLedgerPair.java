package dev.arpit.splitwise.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLedgerPair {
  private User user;
  private double ledger;
}
