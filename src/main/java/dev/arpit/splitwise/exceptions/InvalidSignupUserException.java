package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidSignupUserException extends BaseException {
  public InvalidSignupUserException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
