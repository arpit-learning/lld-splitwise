package dev.arpit.splitwise.repositories;

import dev.arpit.splitwise.models.ExpenseLedger;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpenseLedgerRepository extends JpaRepository<@NonNull ExpenseLedger, @NonNull Long> {
}
