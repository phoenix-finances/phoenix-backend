package io.omni.financia.services;

import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.UnitTransaction;
import io.omni.financia.dto.UnitTransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnitTransactionService {
    List<UnitTransactionDto>getUnitTransactionOfTransaction(Long transactionId);
    List<UnitTransactionDto>getUnitTransactionOfLedger(Long ledgerId);
    UnitTransaction createUnitTransaction(UnitTransaction data);
    void deleteUnitTransaction(Long id);
}
