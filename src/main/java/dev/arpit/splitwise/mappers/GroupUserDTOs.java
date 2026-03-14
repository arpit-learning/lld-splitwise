package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupUser;

import java.util.List;

public class GroupUserDTOs {
  public static AddGroupUserResponseDto getAddGroupUserResponseDto (GroupUser groupUser) {
    return new AddGroupUserResponseDto(
        GroupDTOs.getGroupResponseDto(groupUser.getGroup()),
        UserDTOs.getUserResponseDto(groupUser.getUser()),
        UserDTOs.getUserResponseDto(groupUser.getAddedBy())
    );
  }

  public static FetchGroupUsersResponseDto getFetchGroupUsersResponseDto (Group group, List<GroupUser> groupUsers) {
    GroupResponseDto groupResponseDto = GroupDTOs.getGroupResponseDto(group);
    if(groupUsers.isEmpty()) {
      return new FetchGroupUsersResponseDto(groupResponseDto, List.of());
    }

    return new FetchGroupUsersResponseDto(
      groupResponseDto,
      groupUsers.stream().map(GroupUserDTOs::getGroupUserResponseDto).toList()
    );
  }

  public static AddGroupUsersResponseDto getAddGroupUsersResponseDto (List<GroupUser> groupUsers) {
    return new AddGroupUsersResponseDto(
        GroupDTOs.getGroupResponseDto(groupUsers.getFirst().getGroup()),
        groupUsers.stream().map(groupUser -> UserDTOs.getUserResponseDto(groupUser.getUser())).toList(),
        UserDTOs.getUserResponseDto(groupUsers.getFirst().getAddedBy())
    );
  }

  public static GroupUserResponseDto getGroupUserResponseDto(GroupUser groupUser) {
    return new GroupUserResponseDto(
        groupUser.getUser().getName(),
        groupUser.getUser().getEmail(),
        groupUser.getUser().getMobile(),
        groupUser.getGroupUserType().toString()
    );
  }
}
