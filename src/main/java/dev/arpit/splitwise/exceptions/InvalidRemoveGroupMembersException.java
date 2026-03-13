package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidRemoveGroupMembersException extends BaseException {
  public InvalidRemoveGroupMembersException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
