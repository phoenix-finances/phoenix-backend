package io.omni.financia.repository;

import io.omni.financia.domains.UnitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitTransactionRepo extends JpaRepository<UnitTransaction, Long> {
    List<UnitTransaction>findUnitTransactionByTransactionId(Long id);
    List<UnitTransaction>findUnitTransactionByLedgerId(Long id);
}
