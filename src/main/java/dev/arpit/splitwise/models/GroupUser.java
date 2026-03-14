package dev.arpit.splitwise.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sw_group_user")
public class GroupUser extends BaseModel {
  @ManyToOne(optional = false)
  @JoinColumn(name = "group_id")
  private Group group;
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne(optional = false)
  @JoinColumn(name = "added_by_user_id")
  private User addedBy;
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private GroupUserType groupUserType;
}
