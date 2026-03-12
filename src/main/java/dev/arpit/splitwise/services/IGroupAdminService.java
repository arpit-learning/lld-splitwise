package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.InvalidGroupAdminException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupAdmin;
import dev.arpit.splitwise.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IGroupAdminService {
  GroupAdmin save(GroupAdmin groupAdmin) throws InvalidGroupAdminException;
  GroupAdmin findByGroupAndUser(Group group, User user) throws UnAuthorizedAccessException;
  List<GroupAdmin> findAllByGroup(Group group);
  void deleteAll(List<GroupAdmin> groupAdmins);
  boolean doesExists(Group group, User admin);
}
