package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.User;

public class GroupDTOs {
  public static GroupResponseDto getGroupResponseDto(Group group) {
    User createdBy = group.getGroupCreatedBy();
    UserResponseDto createByDto = UserDTOs.getUserResponseDto(createdBy);
    return new GroupResponseDto(
        group.getId(),
        group.getName(),
        group.getDescription(),
        createByDto
    );
  }

  public static CreateGroupResponseDto getCreateGroupResponseDto(GroupResponseDto groupResponseDto) {
    return new CreateGroupResponseDto(groupResponseDto);
  }

  public static DeleteGroupResponseDto getDeleteGroupResponseDto(GroupResponseDto groupResponseDto) {
    return new DeleteGroupResponseDto(groupResponseDto);
  }
}
