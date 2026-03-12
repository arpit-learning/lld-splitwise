package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidAddGroupMemberException extends BaseException {
  public InvalidAddGroupMemberException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
