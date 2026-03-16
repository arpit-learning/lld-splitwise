package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidCreateExpenseException extends BaseException {
  public InvalidCreateExpenseException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}
