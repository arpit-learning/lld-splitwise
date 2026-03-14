package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class NoGroupUserException extends BaseException {
  public NoGroupUserException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
