package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.InvalidLoginUserException;
import dev.arpit.splitwise.exceptions.NoGroupMemberException;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.models.User;

public interface IUserService {
  User signupUser(User user) throws NoGroupMemberException;
  User loginUser(User user) throws InvalidLoginUserException;
  User updateUser(long userId, User user) throws InvalidUserIdException;
  void deleteUser(long userId) throws InvalidUserIdException;
  User findById(long userId) throws InvalidUserIdException;
}
