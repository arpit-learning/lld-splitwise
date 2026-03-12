package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.NoGroupMemberException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import dev.arpit.splitwise.models.User;

import java.util.List;

public interface IGroupMemberService {
  List<GroupMember> findAllByGroup(Group group);
  void deleteAll(List<GroupMember> groupMembers);
  GroupMember addGroupMember (Group group, User member, User admin) throws UnAuthorizedAccessException;
  GroupMember removeGroupMember (Group group, User member, User admin) throws UnAuthorizedAccessException, NoGroupMemberException;
  GroupMember findByGroupAndUser (Group group, User user) throws NoGroupMemberException;
}
