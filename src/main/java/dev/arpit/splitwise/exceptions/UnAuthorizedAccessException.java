package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class UnAuthorizedAccessException extends BaseException {
  public UnAuthorizedAccessException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
