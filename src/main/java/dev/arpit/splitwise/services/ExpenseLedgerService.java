package dev.arpit.splitwise.services;

import dev.arpit.splitwise.models.ExpenseLedger;
import dev.arpit.splitwise.repositories.ExpenseLedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseLedgerService implements IExpenseLedgerService {
  @Autowired
  private ExpenseLedgerRepository expenseLedgerRepository;

  @Override
  public List<ExpenseLedger> saveAll(List<ExpenseLedger> expenseLedgers) {
    return expenseLedgerRepository.saveAll(expenseLedgers);
  }
}
