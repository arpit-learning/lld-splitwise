package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.InvalidCreateExpenseException;
import dev.arpit.splitwise.exceptions.InvalidFetchExpensesException;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.models.Expense;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.UserIdLedgerPair;
import dev.arpit.splitwise.models.UserLedgerPair;

import java.util.List;
import java.util.Map;

public interface IExpenseService {
  List<Expense> findAllByGroup(Group group);
  Expense createExpense(Group group, String description, double amount, List<UserIdLedgerPair> ledgers) throws InvalidCreateExpenseException, InvalidUserIdException;
  List<Expense> fetchExpenses(Group group) throws InvalidFetchExpensesException;
}
