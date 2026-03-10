package dev.arpit.splitwise.dtos;

import dev.arpit.splitwise.models.Group;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteGroupResponseDto {
    private GroupResponseDto groupResponseDto;
}
