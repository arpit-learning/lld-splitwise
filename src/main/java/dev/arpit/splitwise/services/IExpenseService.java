package dev.arpit.splitwise.services;

import dev.arpit.splitwise.models.Expense;
import dev.arpit.splitwise.models.Group;

import java.util.List;

public interface IExpenseService {
  List<Expense> findAllByGroup(Group group);
}
