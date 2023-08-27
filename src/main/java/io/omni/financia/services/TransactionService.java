package io.omni.financia.services;

import io.omni.financia.domains.Transaction;
import io.omni.financia.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionDto>getUnitTransactionOfTransaction(Long transactionId);
    List<TransactionDto>getUnitTransactionOfLedger(Long ledgerId);
    Transaction createUnitTransaction(Transaction data);
    void deleteUnitTransaction(Long id);
}
