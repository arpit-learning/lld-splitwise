package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidGroupUserException extends BaseException {
  public InvalidGroupUserException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
