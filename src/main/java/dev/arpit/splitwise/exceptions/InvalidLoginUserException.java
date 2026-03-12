package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidLoginUserException extends BaseException {
  public InvalidLoginUserException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
