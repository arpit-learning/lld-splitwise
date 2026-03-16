package dev.arpit.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sw_expense")
public class Expense extends BaseModel {
  @ManyToOne(optional = false)
  @JoinColumn(name = "group_id", referencedColumnName = "id")
  private Group group;
  private String description;
  private double amount;
  @OneToMany(mappedBy = "expense")
  @ToString.Exclude
  private List<ExpenseLedger> expenseLedgers;
}
