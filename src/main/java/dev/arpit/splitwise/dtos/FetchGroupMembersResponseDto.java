package dev.arpit.splitwise.dtos;

import dev.arpit.splitwise.models.Group;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FetchGroupMembersResponseDto {
    private GroupResponseDto group;
    private List<UserResponseDto> members;
}
