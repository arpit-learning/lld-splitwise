package dev.arpit.splitwise.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class ResponseDto<T> {
  private T data;
  private MetaDataDto meta;
}
