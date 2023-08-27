package io.omni.financia.repository;

import io.omni.financia.domains.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger,Long> {
    //List<Ledger>findLedgerByAppUserEmail(String email);
}
