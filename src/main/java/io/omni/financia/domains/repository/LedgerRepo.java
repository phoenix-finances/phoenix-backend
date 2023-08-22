package io.omni.financia.domains.repository;

import io.omni.financia.domains.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepo extends JpaRepository<Ledger,Long> {
}
