package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidUserIdException extends BaseException {
  public InvalidUserIdException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
