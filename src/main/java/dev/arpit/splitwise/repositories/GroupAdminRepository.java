package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.GroupAdmin;
import dev.arpit.splitwise.models.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupAdminRepository extends JpaRepository<@NonNull GroupAdmin, @NonNull Long> {
  Optional<GroupAdmin> findByGroupAndAdmin(@NonNull Group group, @NonNull User admin);
  List<GroupAdmin> findAllByGroup(@NonNull Group group);
}
