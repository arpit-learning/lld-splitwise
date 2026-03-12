package dev.arpit.splitwise.controllers;

import dev.arpit.splitwise.dtos.*;
import dev.arpit.splitwise.exceptions.BaseException;
import dev.arpit.splitwise.exceptions.InvalidCreateGroupRequestDtoException;
import dev.arpit.splitwise.exceptions.InvalidDeleteGroupRequestDtoException;
import dev.arpit.splitwise.exceptions.InvalidGroupIdException;
import dev.arpit.splitwise.mappers.GroupDTOs;
import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.services.IGroupService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IGroupController {
  ResponseEntity<@NonNull ResponseDto<CreateGroupResponseDto>> createGroup(@RequestBody CreateGroupRequestDto requestDto);
  ResponseEntity<@NonNull ResponseDto<DeleteGroupResponseDto>> deleteGroup(@PathVariable Long groupId, @RequestBody DeleteGroupRequestDto requestDto);
}
