package io.omni.financia.repository;

import io.omni.financia.domains.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByTransactionGroupId(Long id);

    List<Transaction> findTransactionByLedgerId(Long id);
}
