package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;

public class InvalidFetchExpensesException extends BaseException {
  public InvalidFetchExpensesException (ResponseCode code, String message, String displayMessage) {
    super(code, message, displayMessage);
  }
}

