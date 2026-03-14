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
@Entity(name = "sw_group")
public class Group extends BaseModel {
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String description;
  @ManyToOne(optional = false)
  @JoinColumn(name = "group_created_by", referencedColumnName = "id")
  private User groupCreatedBy;
}
