package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidDeleteUserRequestDtoException extends BaseException {
  public InvalidDeleteUserRequestDtoException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
