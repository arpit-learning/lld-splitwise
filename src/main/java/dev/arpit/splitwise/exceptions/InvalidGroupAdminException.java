package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidGroupAdminException extends BaseException {
  public InvalidGroupAdminException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
