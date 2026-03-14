package dev.arpit.splitwise.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sw_expense_ledger")
public class ExpenseLedger extends BaseModel {
  @ManyToOne
  @JoinColumn(name = "expense_id", referencedColumnName = "id")
  private Expense expense;
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  private double amount;
  @Enumerated(EnumType.STRING)
  private ExpenseType expenseType;
}
