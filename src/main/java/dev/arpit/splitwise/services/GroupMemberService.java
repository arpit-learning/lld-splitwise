package dev.arpit.splitwise.services;

import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import dev.arpit.splitwise.repositories.GroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberService implements IGroupMemberService {

  @Autowired
  private GroupMemberRepository groupMemberRepository;

  @Override
  public List<GroupMember> findAllByGroup(Group group) {
    return groupMemberRepository.findAllByGroup(group);
  }

  @Override
  public void deleteAll(List<GroupMember> groupMembers) {
    groupMemberRepository.deleteAll(groupMembers);
  }
}
