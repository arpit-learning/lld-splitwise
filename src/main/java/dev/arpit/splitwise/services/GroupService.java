package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.InvalidGroupAdminException;
import dev.arpit.splitwise.exceptions.InvalidGroupIdException;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupAdmin;
import dev.arpit.splitwise.models.GroupMember;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService implements IGroupService {
  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private IUserService iUserService;
  @Autowired
  private IGroupAdminService iGroupAdminService;
  @Autowired
  private IGroupMemberService iGroupMemberService;

  @Override
  public Group save(Group group) {
    return groupRepository.save(group);
  }

  @Override
  public Group findById(long groupId) throws InvalidGroupIdException {
    return groupRepository.findById(groupId).orElseThrow(() -> new InvalidGroupIdException(ResponseCode.SW_ERR_400, "group with groupId: " + groupId + " not found", "Group not found. Please pass correct groupId in the payload."));
  }

  @Override
  public void delete(Group group) {
    groupRepository.delete(group);
  }

  @Override
  @Transactional
  public Group createGroup(String groupName, String description, long userId) throws InvalidUserIdException, InvalidGroupAdminException {
    User admin = iUserService.findById(userId);

    Group group = new Group(groupName, description, admin);
    group = this.save(group);

    GroupAdmin groupAdmin = new GroupAdmin(group, admin, admin);
    iGroupAdminService.save(groupAdmin);

    return group;
  }

  @Override
  @Transactional
  public Group deleteGroup(long groupId, long userId) throws InvalidGroupIdException, InvalidUserIdException, UnAuthorizedAccessException {
    Group group = this.findById(groupId);
    User admin = iUserService.findById(userId);
    iGroupAdminService.findByGroupAndUser(group, admin);
    List<GroupMember> groupMembers = iGroupMemberService.findAllByGroup(group);
    List<GroupAdmin> groupAdmins = iGroupAdminService.findAllByGroup(group);

    iGroupAdminService.deleteAll(groupAdmins);
    iGroupMemberService.deleteAll(groupMembers);
    this.delete(group);
    return group;
  }
}
