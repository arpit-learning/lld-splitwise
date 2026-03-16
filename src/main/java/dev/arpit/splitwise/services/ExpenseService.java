package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.InvalidCreateExpenseException;
import dev.arpit.splitwise.exceptions.InvalidFetchExpensesException;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.models.*;
import dev.arpit.splitwise.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService implements IExpenseService {
  @Autowired
  private ExpenseRepository expenseRepository;
  @Autowired
  private IGroupService iGroupService;
  @Autowired
  private IUserService iUserService;
  @Autowired
  private IExpenseLedgerService iExpenseLedgerService;

  @Override
  public List<Expense> findAllByGroup (Group group) {
    return expenseRepository.findAllByGroup(group);
  }

  @Override
  public Expense createExpense(Group group, String description, double amount, List<UserIdLedgerPair> ledgers) throws InvalidCreateExpenseException, InvalidUserIdException {
    Expense expense = new Expense(
        group,
        description,
        amount,
        null
    );
    expense = expenseRepository.save(expense);

    List<ExpenseLedger> expenseLedgers = new ArrayList<>();

    for(UserIdLedgerPair ledger: ledgers) {
      if(ledger.getLedger() == 0) {
        throw new InvalidCreateExpenseException(
            ResponseCode.SW_ERR_400,
            "Amount for ledger for user with userId " + ledger.getUserId() + " is 0.",
            "Please make sure the amount for each ledger should be non zero."
        );
      }
      User user = iUserService.findById(ledger.getUserId());
      ExpenseType type = null;
      if(ledger.getLedger() > 0) {
        type = ExpenseType.PAID;
      } else if( ledger.getLedger() < 0) {
        type = ExpenseType.OWED;
      }

      expenseLedgers.add(new ExpenseLedger(
          expense,
          user,
          Math.abs(ledger.getLedger()),
          type
      ));
    }
    expenseLedgers = iExpenseLedgerService.saveAll(expenseLedgers);

    expense.setExpenseLedgers(expenseLedgers);
    expenseRepository.save(expense);

    group.getExpenses().add(expense);
    iGroupService.save(group);
    return expenseRepository.save(expense);
  }

  @Override
  public List<Expense> fetchExpenses (Group group) {
    return expenseRepository.findAllByGroup(group);
  }
}
