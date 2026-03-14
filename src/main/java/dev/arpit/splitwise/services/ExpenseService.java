package dev.arpit.splitwise.services;

import dev.arpit.splitwise.models.Expense;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements IExpenseService {
  @Autowired
  private ExpenseRepository expenseRepository;

  @Override
  public List<Expense> findAllByGroup (Group group) {
    return expenseRepository.findAllByGroup(group);
  }
}
