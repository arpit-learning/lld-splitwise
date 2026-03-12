package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.InvalidGroupAdminException;
import dev.arpit.splitwise.exceptions.InvalidGroupIdException;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.User;

public interface IGroupService {
  Group save(Group group);
  Group findById(long groupId) throws InvalidGroupIdException;
  void delete(Group group);
  Group createGroup(String groupName, String description, User admin) throws InvalidGroupAdminException;
  Group deleteGroup(long groupId, User admin) throws InvalidGroupIdException, UnAuthorizedAccessException;
}
