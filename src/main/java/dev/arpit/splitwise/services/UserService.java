package dev.arpit.splitwise.services;

import dev.arpit.splitwise.configs.BCryptEncoder;
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
  @Autowired
  private BCryptEncoder bCryptEncoder;

  @Override
  public User signupUser(User user) {
    String newPass = bCryptEncoder.encode(user.getPassword());
    user.setPassword(newPass);
    return userRepository.save(user);
  }

  @Override
  public User updateUser (long userId, User user) throws InvalidUserIdException {
    User existingUser = userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(ResponseCode.SW_ERR_400,"user with userId: " + userId + " not found", "User not found. Please pass correct userId in the payload."));
    if(user.getName() != null) existingUser.setName(user.getName());
    if(user.getEmail() != null) existingUser.setEmail(user.getEmail());
    if(user.getMobile() != null) existingUser.setMobile(user.getMobile());
    return userRepository.save(existingUser);
  }

  @Override
  public void deleteUser(Long userId) throws InvalidUserIdException {
    User user = userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(ResponseCode.SW_ERR_400,"user with userId: " + userId + " not found", "User not found. Please pass correct userId in the payload."));
    userRepository.delete(user);
  }

  @Override
  public User findById(long userId) throws InvalidUserIdException {
    return userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(ResponseCode.SW_ERR_400,"user with userId: " + userId + " not found", "User not found. Please pass correct userId in the payload."));
  }
}
