package dev.arpit.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RemoveGroupUsersRequestDto {
  private Long adminId;
  private List<Long> userIds;
}
