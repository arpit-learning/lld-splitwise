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
  @ManyToOne(optional = false)
  @JoinColumn(name = "expense_id", referencedColumnName = "id")
  private Expense expense;
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  @Column(nullable = false)
  private double amount;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ExpenseType expenseType;
}
