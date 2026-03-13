package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;

import java.util.List;

public class GroupMemberDTOs {
  public static AddGroupMemberResponseDto getAddGroupMemberResponseDto(GroupMember groupMember) {
    return new AddGroupMemberResponseDto(
      GroupDTOs.getGroupResponseDto(groupMember.getGroup()),
      UserDTOs.getUserResponseDto(groupMember.getMember()),
      UserDTOs.getUserResponseDto(groupMember.getAddedBy())
    );
  }

  public static FetchGroupMembersResponseDto getFetchGroupMembersResponseDto(Group group, List<GroupMember> groupMembers) {
    GroupResponseDto groupResponseDto = GroupDTOs.getGroupResponseDto(group);
    if(groupMembers.isEmpty()) {
      return new FetchGroupMembersResponseDto(groupResponseDto, List.of());
    }

    List<UserResponseDto> members = groupMembers.stream().map(m -> UserDTOs.getUserResponseDto(m.getMember())).toList();

    return new FetchGroupMembersResponseDto(
      groupResponseDto,
      members
    );
  }

  public static AddGroupMembersResponseDto getAddGroupMembersResponseDto(List<GroupMember> groupMembers) {
    return new AddGroupMembersResponseDto(
        GroupDTOs.getGroupResponseDto(groupMembers.getFirst().getGroup()),
        groupMembers.stream().map(groupMember -> UserDTOs.getUserResponseDto(groupMember.getMember())).toList(),
        UserDTOs.getUserResponseDto(groupMembers.getFirst().getAddedBy())
    );
  }
}
