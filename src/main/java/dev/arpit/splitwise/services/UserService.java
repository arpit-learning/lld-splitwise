package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.InvalidUserIdException;
import dev.arpit.splitwise.models.User;
import dev.arpit.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findById(long userId) throws InvalidUserIdException {
    return userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(ResponseCode.SW_ERR_400,"user with userId: " + userId + " not found", "User not found. Please pass correct userId in the payload."));
  }
}
