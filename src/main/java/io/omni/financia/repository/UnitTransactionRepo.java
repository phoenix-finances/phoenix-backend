package io.omni.financia.repository;

import io.omni.financia.domains.UnitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTransactionRepo extends JpaRepository<UnitTransaction, Long> {
}
