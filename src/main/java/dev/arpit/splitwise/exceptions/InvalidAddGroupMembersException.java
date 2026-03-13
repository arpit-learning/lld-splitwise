package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidAddGroupMembersException extends BaseException {
  public InvalidAddGroupMembersException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
