package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupUser;
import dev.arpit.splitwise.models.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupUserRepository extends JpaRepository<@NonNull GroupUser, @NonNull Long> {
  Optional<GroupUser> findByGroupAndUser(@NonNull Group group, @NonNull User user);
  List<GroupUser> findAllByGroup(@NonNull Group group);
}
