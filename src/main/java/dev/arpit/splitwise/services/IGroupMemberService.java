package dev.arpit.splitwise.services;

import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IGroupMemberService {
  List<GroupMember> findAllByGroup(Group group);
  void deleteAll(List<GroupMember> groupMembers);
}
