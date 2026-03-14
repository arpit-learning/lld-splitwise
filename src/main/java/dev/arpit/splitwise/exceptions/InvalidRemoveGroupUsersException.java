package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidRemoveGroupUsersException extends BaseException {
  public InvalidRemoveGroupUsersException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
