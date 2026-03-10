package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidCreateGroupRequestDtoException extends BaseException {
  public InvalidCreateGroupRequestDtoException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
