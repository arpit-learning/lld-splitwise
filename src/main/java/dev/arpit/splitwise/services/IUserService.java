package dev.arpit.splitwise.services;

import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IUserService {
  User createUser(User user);
  User findById(long userId) throws InvalidUserIdException;
}
