package dev.arpit.splitwise.services;

import dev.arpit.splitwise.models.ExpenseLedger;

import java.util.List;

public interface IExpenseLedgerService {
  List<ExpenseLedger> saveAll(List<ExpenseLedger> expenseLedgers);
}
