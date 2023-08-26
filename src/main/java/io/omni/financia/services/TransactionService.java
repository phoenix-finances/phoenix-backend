package io.omni.financia.services;

import io.omni.financia.domains.TransactionGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionGroup> getAll(Long id);

    TransactionGroup addTransaction(TransactionGroup transactionDto);

    void deleteTransaction(Long id);
}
