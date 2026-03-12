package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.InvalidAddGroupMemberException;
import dev.arpit.splitwise.exceptions.NoGroupMemberException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.repositories.GroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberService implements IGroupMemberService {

  @Autowired
  private GroupMemberRepository groupMemberRepository;
  @Autowired
  private IGroupAdminService iGroupAdminService;

  @Override
  public List<GroupMember> findAllByGroup(Group group) {
    return groupMemberRepository.findAllByGroup(group);
  }

  @Override
  public void deleteAll(List<GroupMember> groupMembers) {
    groupMemberRepository.deleteAll(groupMembers);
  }

  @Override
  public GroupMember addGroupMember (Group group, User member, User admin) throws UnAuthorizedAccessException, InvalidAddGroupMemberException {
    iGroupAdminService.findByGroupAndUser(group, admin);
    if(iGroupAdminService.doesExists(group, member)) {
      throw new InvalidAddGroupMemberException(ResponseCode.SW_ERR_400, "member with id " + member.getId() + " is already a admin of the group", "Please pass correct member id.");
    }
    if(groupMemberRepository.findByGroupAndMember(group, member).isPresent()) {
      throw new InvalidAddGroupMemberException(ResponseCode.SW_ERR_400, "member with id " + member.getId() + " is already a member of the group", "Please pass correct member id.");
    }
    return groupMemberRepository.save(new GroupMember(group, member, admin));
  }

  @Override
  public GroupMember removeGroupMember (Group group, User member, User admin) throws UnAuthorizedAccessException, NoGroupMemberException {
    iGroupAdminService.findByGroupAndUser(group, admin);
    GroupMember groupMember = this.findByGroupAndUser(group, member);
    groupMemberRepository.delete(groupMember);
    return groupMember;
  }

  @Override
  public GroupMember findByGroupAndUser (Group group, User user) throws NoGroupMemberException {
    return groupMemberRepository.findByGroupAndMember(group, user).orElseThrow(() -> new NoGroupMemberException(
        ResponseCode.SW_ERR_400,
        "user with id " + user.getId() + " is not a member of group with id " + group.getId(),
        "User is not a member of the group"
    ));
  }
}
