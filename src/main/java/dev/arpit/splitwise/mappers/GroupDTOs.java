package dev.arpit.splitwise.mappers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.SuggestedTransaction;
import dev.arpit.splitwise.models.User;

import java.util.List;

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

  public static SettleUpGroupResponseDto getSettleUpGroupResponseDto(List<SuggestedTransaction> suggestionTransactions) {
    return new SettleUpGroupResponseDto(
        GroupDTOs.getGroupResponseDto(suggestionTransactions.getFirst().getGroup()),
        suggestionTransactions.stream().map(GroupDTOs::getSuggestedTransactionDto).toList()
    );
  }

  public static SuggestedTransactionDto getSuggestedTransactionDto(SuggestedTransaction suggestedTransaction) {
    return new SuggestedTransactionDto(
        UserDTOs.getUserResponseDto(suggestedTransaction.getPaidFrom()),
        UserDTOs.getUserResponseDto(suggestedTransaction.getPaidTo()),
        GroupDTOs.getGroupResponseDto(suggestedTransaction.getGroup()),
        suggestedTransaction.getAmount()
    );
  }
}
