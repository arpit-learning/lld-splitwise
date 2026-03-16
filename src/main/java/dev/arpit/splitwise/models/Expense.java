package dev.arpit.splitwise.models;

import jakarta.persistence.*;
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
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private double amount;
  @OneToMany(mappedBy = "expense")
  @ToString.Exclude
  private List<ExpenseLedger> expenseLedgers;
}
