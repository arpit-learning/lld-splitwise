package dev.arpit.splitwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sw_suggested_transaction")
public class SuggestedTransaction extends BaseModel {
  @ManyToOne(optional = false)
  @JoinColumn(name = "paid_from_id", referencedColumnName = "id")
  private User paidFrom;
  @ManyToOne(optional = false)
  @JoinColumn(name = "paid_to_id", referencedColumnName = "id")
  private User paidTo;
  @Column(nullable = false)
  private double amount;
  @ManyToOne(optional = false)
  @JoinColumn(name = "group_id", referencedColumnName = "id")
  private Group group;
}
