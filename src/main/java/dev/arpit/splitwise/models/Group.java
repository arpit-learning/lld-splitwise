package dev.arpit.splitwise.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sw_group")
public class Group extends BaseModel {
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String description;
  @ManyToOne(optional = false)
  @JoinColumn(name = "group_created_by", referencedColumnName = "id")
  private User groupCreatedBy;
  @OneToMany(mappedBy = "group")
  @ToString.Exclude
  private List<Expense> expenses;
}
