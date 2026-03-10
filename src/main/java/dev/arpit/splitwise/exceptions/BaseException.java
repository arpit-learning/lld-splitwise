package dev.arpit.splitwise.exceptions;

import dev.arpit.splitwise.dtos.ResponseCode;
import lombok.Data;

@Data
public class BaseException extends Exception {
  private String displayMessage;
  private ResponseCode code;

  public BaseException(ResponseCode code, String message, String displayMessage) {
    this.code = code;
    this.displayMessage = displayMessage;
    super(message);
  }
}
