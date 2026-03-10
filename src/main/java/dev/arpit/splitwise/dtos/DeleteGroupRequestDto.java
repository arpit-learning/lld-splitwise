package dev.arpit.splitwise.dtos;

import lombok.Data;

@Data
public class DeleteGroupRequestDto {
    private Long groupId;
    private Long userId;
}
