package dev.arpit.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AddGroupUsersRequestDto {
  private Long adminId;
  private List<Long> userIds;
}
