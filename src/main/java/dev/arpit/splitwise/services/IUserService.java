package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IUserService {
  User signupUser(User user);
  User updateUser(long userId, User user) throws InvalidUserIdException;
  void deleteUser(Long userId) throws InvalidUserIdException;
  User findById(long userId) throws InvalidUserIdException;
}
