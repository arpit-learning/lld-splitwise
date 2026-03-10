package dev.arpit.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetaDataDto {
  private ResponseCode code;
  private String message;
  private String displayMessage;
  private String requestId;
  private String responseId;
}
