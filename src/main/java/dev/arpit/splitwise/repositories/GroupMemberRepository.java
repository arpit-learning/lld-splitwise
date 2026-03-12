package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import dev.arpit.splitwise.models.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<@NonNull GroupMember, @NonNull Long> {
  Optional<GroupMember> findByGroupAndMember(@NonNull Group group, @NonNull User member);
  List<GroupMember> findAllByGroup(@NonNull Group group);
}
