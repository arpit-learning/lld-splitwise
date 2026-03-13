package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.*;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import dev.arpit.splitwise.models.User;

import java.util.List;

public interface IGroupMemberService {
  List<GroupMember> findAllByGroup(Group group);
  void deleteAll(List<GroupMember> groupMembers);
  GroupMember addGroupMember (Group group, User member, User admin) throws UnAuthorizedAccessException, InvalidAddGroupMemberException;
  GroupMember removeGroupMember (Group group, User member, User admin) throws UnAuthorizedAccessException, NoGroupMemberException;
  List<GroupMember> addGroupMembers (Group group, List<User> members, User admin) throws UnAuthorizedAccessException, InvalidAddGroupMembersException;
  List<GroupMember> removeGroupMembers (Group group, List<User> members, User admin) throws UnAuthorizedAccessException, NoGroupMemberException, InvalidRemoveGroupMembersException;
  GroupMember findByGroupAndUser (Group group, User user) throws NoGroupMemberException;
}
