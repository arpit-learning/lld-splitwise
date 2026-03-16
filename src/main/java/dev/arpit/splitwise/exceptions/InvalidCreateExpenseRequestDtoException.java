package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidCreateExpenseRequestDtoException extends BaseException {
  public InvalidCreateExpenseRequestDtoException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
