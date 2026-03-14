package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.Expense;
import dev.arpit.splitwise.models.Group;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<@NonNull Expense, @NonNull Long> {
  List<Expense> findAllByGroup(Group group);
}
