package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<@NonNull User, @NonNull Long> {
}
