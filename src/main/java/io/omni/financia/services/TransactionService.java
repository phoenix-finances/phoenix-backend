package io.omni.financia.services;

import io.omni.financia.domains.Transaction;
import io.omni.financia.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<Transaction> getAll(Long id);

    Transaction addTransaction(TransactionDto transactionDto);

    void deleteTransaction(Long id);
}
