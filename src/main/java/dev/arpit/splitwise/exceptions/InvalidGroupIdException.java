package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidGroupIdException extends BaseException {
  public InvalidGroupIdException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
