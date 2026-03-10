package dev.arpit.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sw_group_admin")
public class GroupAdmin extends BaseModel {
  @ManyToOne
  @JoinColumn(name = "group_id")
  private Group group;
  @ManyToOne
  @JoinColumn(name = "admin_id")
  private User admin;
  @ManyToOne
  @JoinColumn(name = "added_by_id")
  private User addedBy;
}
