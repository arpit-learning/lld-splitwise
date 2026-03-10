package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupMember;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<@NonNull GroupMember, @NonNull Long> {
  List<GroupMember> findAllByGroup(@NonNull Group group);
}
