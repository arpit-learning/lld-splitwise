package dev.arpit.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AddGroupMembersRequestDto {
  private Long adminId;
  private List<Long> memberIds;
}
