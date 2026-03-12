package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.AddGroupMemberResponseDto;
import dev.arpit.splitwise.dtos.FetchGroupMembersResponseDto;
import dev.arpit.splitwise.dtos.UserResponseDto;
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

  public static FetchGroupMembersResponseDto getFetchGroupMembersResponseDto(List<GroupMember> groupMembers) {
    if(groupMembers.isEmpty()) {
      return new FetchGroupMembersResponseDto(null, null);
    }

    List<UserResponseDto> members = groupMembers.stream().map(m -> UserDTOs.getUserResponseDto(m.getMember())).toList();

    return new FetchGroupMembersResponseDto(
      GroupDTOs.getGroupResponseDto(groupMembers.getFirst().getGroup()),
      members
    );
  }
}
