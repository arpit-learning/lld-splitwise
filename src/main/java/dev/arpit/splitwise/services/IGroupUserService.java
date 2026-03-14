package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.*;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupUser;
import dev.arpit.splitwise.models.User;

import java.util.List;

public interface IGroupUserService {
  List<GroupUser> findAllByGroup(Group group);
  void deleteAll(List<GroupUser> groupUsers);
  GroupUser addGroupUser (Group group, User user, User admin) throws NoGroupUserException, UnAuthorizedAccessException, InvalidGroupUserException, InvalidAddGroupUserException;
  GroupUser removeGroupUser (Group group, User user, User admin) throws NoGroupUserException, UnAuthorizedAccessException;
  List<GroupUser> addGroupUsers (Group group, List<User> users, User admin) throws NoGroupUserException, UnAuthorizedAccessException, InvalidAddGroupUsersException;
  List<GroupUser> removeGroupUsers (Group group, List<User> users, User admin) throws NoGroupUserException, UnAuthorizedAccessException, InvalidRemoveGroupUsersException;
  GroupUser findByGroupAndUser (Group group, User user) throws NoGroupUserException;
  GroupUser save(GroupUser groupUser) throws InvalidGroupUserException;
  boolean doesExists (Group group, User user);
  List<GroupUser> saveAll(List<GroupUser> groupUsers);
  boolean isUserAdmin(Group group, User user) throws NoGroupUserException;
}
