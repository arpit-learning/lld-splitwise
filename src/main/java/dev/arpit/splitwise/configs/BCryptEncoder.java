package dev.arpit.splitwise.configs;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncoder implements PasswordEncoder {
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public BCryptEncoder() {
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public @Nullable String encode (@Nullable CharSequence rawPassword) {
    return bCryptPasswordEncoder.encode(rawPassword);
  }

  @Override
  public boolean matches (@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
    return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
  }
}
