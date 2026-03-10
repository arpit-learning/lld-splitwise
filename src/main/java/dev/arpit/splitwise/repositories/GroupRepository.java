package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.Group;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<@NonNull Group, @NonNull Long> {
}
