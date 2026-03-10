package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.InvalidGroupAdminException;
import dev.arpit.splitwise.exceptions.InvalidGroupIdException;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.Group;
import org.springframework.stereotype.Service;

public interface IGroupService {
  Group save(Group group);
  Group findById(long groupId) throws InvalidGroupIdException;
  void delete(Group group);
  Group createGroup(String groupName, String description, long userId) throws InvalidUserIdException, InvalidGroupAdminException;
  Group deleteGroup(long groupId, long userId) throws InvalidGroupIdException, InvalidUserIdException, UnAuthorizedAccessException;
}
