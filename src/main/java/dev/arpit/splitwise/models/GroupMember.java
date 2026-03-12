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
@Entity(name = "sw_group_member")
public class GroupMember extends BaseModel {
  @ManyToOne
  @JoinColumn(name = "group_id")
  private Group group;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User member;
  @ManyToOne
  @JoinColumn(name = "added_by_id")
  private User addedBy;
}
