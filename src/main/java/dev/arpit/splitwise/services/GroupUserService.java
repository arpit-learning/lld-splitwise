package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.*;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupUser;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.models.GroupUserType;
import dev.arpit.splitwise.repositories.GroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupUserService implements IGroupUserService {

  @Autowired
  private GroupUserRepository groupUserRepository;

  @Override
  public List<GroupUser> findAllByGroup(Group group) {
    return groupUserRepository.findAllByGroup(group);
  }

  @Override
  public void deleteAll(List<GroupUser> groupUsers) {
    groupUserRepository.deleteAll(groupUsers);
  }

  @Override
  public GroupUser addGroupUser (Group group, User user, User admin) throws NoGroupUserException, UnAuthorizedAccessException, InvalidGroupUserException, InvalidAddGroupUserException {
    GroupUser adminUser = this.findByGroupAndUser(group, admin);
    if(adminUser.getGroupUserType() != GroupUserType.ADMIN) {
      throw new UnAuthorizedAccessException(ResponseCode.SW_ERR_403, "admin with id " + admin.getId() + " is not an admin of the group", "Please pass correct admin id.");
    }
    if(this.doesExists(group, user)) {
      throw new InvalidAddGroupUserException(ResponseCode.SW_ERR_400, "user with id " + user.getId() + " is already a user of the group", "Please pass correct user id.");
    }
    return this.save(new GroupUser(group, user, admin, GroupUserType.MEMBER));
  }

  @Override
  public GroupUser removeGroupUser (Group group, User user, User admin) throws UnAuthorizedAccessException, NoGroupUserException {
    GroupUser adminUser = this.findByGroupAndUser(group, admin);
    if(adminUser.getGroupUserType() != GroupUserType.ADMIN) {
      throw new UnAuthorizedAccessException(ResponseCode.SW_ERR_403, "admin with id " + admin.getId() + " is not an admin of the group", "Please pass correct admin id.");
    }
    GroupUser groupUser = this.findByGroupAndUser(group, user);
    groupUserRepository.delete(groupUser);
    return groupUser;
  }

  @Override
  public List<GroupUser> addGroupUsers (Group group, List<User> users, User admin) throws NoGroupUserException, UnAuthorizedAccessException, InvalidAddGroupUsersException {
    GroupUser adminUser = this.findByGroupAndUser(group, admin);
    if(adminUser.getGroupUserType() != GroupUserType.ADMIN) {
      throw new UnAuthorizedAccessException(ResponseCode.SW_ERR_403, "admin with id " + admin.getId() + " is not an admin of the group", "Please pass correct admin id.");
    }
    if(users.isEmpty()) {
      throw new InvalidAddGroupUsersException(ResponseCode.SW_ERR_400, "user ids can't be null or empty", "Please pass correct user ids.");
    }
    for(User user: users) {
      if (this.doesExists(group, user)) {
        throw new InvalidAddGroupUsersException(ResponseCode.SW_ERR_400, "user with id " + user.getId() + " is already a user of the group", "Please pass correct user ids.");
      }
    }

    List<GroupUser> groupUsers = users.stream().map(user -> new GroupUser(group, user, admin, GroupUserType.MEMBER)).toList();
    return this.saveAll(groupUsers);
  }

  @Override
  public List<GroupUser> removeGroupUsers (Group group, List<User> users, User admin) throws UnAuthorizedAccessException, NoGroupUserException, InvalidRemoveGroupUsersException {
    GroupUser adminUser = this.findByGroupAndUser(group, admin);
    if(adminUser.getGroupUserType() != GroupUserType.ADMIN) {
      throw new UnAuthorizedAccessException(ResponseCode.SW_ERR_403, "admin with id " + admin.getId() + " is not an admin of the group", "Please pass correct admin id.");
    }
    if(users.isEmpty()) {
      throw new InvalidRemoveGroupUsersException(ResponseCode.SW_ERR_400, "user ids can't be null or empty", "Please pass correct user ids.");
    }

    List<GroupUser> groupUsers = new ArrayList<>();
    for(User user: users) {
      groupUsers.add(this.findByGroupAndUser(group, user));
    }

    groupUserRepository.deleteAll(groupUsers);
    return groupUsers;
  }

  @Override
  public GroupUser findByGroupAndUser (Group group, User user) throws NoGroupUserException {
    return groupUserRepository.findByGroupAndUser(group, user).orElseThrow(() -> new NoGroupUserException(
        ResponseCode.SW_ERR_400,
        "user with id " + user.getId() + " is not a user of group with id " + group.getId(),
        "User is not a user of the group"
    ));
  }


  @Override
  public GroupUser save(GroupUser groupUser) throws InvalidGroupUserException {
    if(groupUser == null) {
      throw new InvalidGroupUserException(ResponseCode.SW_ERR_400, "groupUser is null", "GroupUser cannot be null. Please pass correct GroupUser object in the payload.");
    }
    return groupUserRepository.save(groupUser);
  }

  @Override
  public List<GroupUser> saveAll(List<GroupUser> groupUsers) {
    return groupUserRepository.saveAll(groupUsers);
  }

  @Override
  public boolean isUserAdmin (Group group, User user) throws NoGroupUserException {
    GroupUser groupUser = this.findByGroupAndUser(group, user);
    return groupUser.getGroupUserType() == GroupUserType.ADMIN;
  }

  @Override
  public boolean doesExists (Group group, User user) {
    return groupUserRepository.findByGroupAndUser(group, user).isPresent();
  }
}
