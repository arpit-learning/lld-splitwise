package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidAddGroupUserException extends BaseException {
  public InvalidAddGroupUserException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
