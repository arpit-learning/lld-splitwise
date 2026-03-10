package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.InvalidGroupAdminException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupAdmin;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.repositories.GroupAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupAdminService implements IGroupAdminService {
  @Autowired
  private GroupAdminRepository groupAdminRepository;

  @Override
  public GroupAdmin save(GroupAdmin groupAdmin) throws InvalidGroupAdminException {
    if(groupAdmin == null) {
      throw new InvalidGroupAdminException(ResponseCode.SW_ERR_400, "groupAdmin is null", "GroupAdmin cannot be null. Please pass correct GroupAdmin object in the payload.");
    }
    if(groupAdmin.getGroup() == null) {
      throw new InvalidGroupAdminException(ResponseCode.SW_ERR_400, "groupAdmin object does not have a group", "GroupAdmin object does not have a group. Please pass correct GroupAdmin object in the payload.");
    }
    if(groupAdmin.getAdmin() == null) {
      throw new InvalidGroupAdminException(ResponseCode.SW_ERR_400, "groupAdmin object does not have an admin", "GroupAdmin object does not have an admin. Please pass correct GroupAdmin object in the payload.");
    }
    if(groupAdmin.getAddedBy() == null) {
      throw new InvalidGroupAdminException(ResponseCode.SW_ERR_400, "groupAdmin object does not have an addedBy", "GroupAdmin object does not have an addedBy. Please pass correct GroupAdmin object in the payload.");
    }
    return groupAdminRepository.save(groupAdmin);
  }

  @Override
  public GroupAdmin findByGroupAndUser(Group group, User user) throws UnAuthorizedAccessException {
    return groupAdminRepository.findByGroupAndAdmin(group, user).orElseThrow(() -> new UnAuthorizedAccessException(ResponseCode.SW_ERR_403, "user is not an admin of the group", "You are not an admin of the group. Please add yourself as an admin of the group."));
  }

  @Override
  public List<GroupAdmin> findAllByGroup(Group group) {
    return groupAdminRepository.findAllByGroup(group);
  }

  @Override
  public void deleteAll(List<GroupAdmin> groupAdmins) {
    groupAdminRepository.deleteAll(groupAdmins);
  }
}
