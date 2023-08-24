package io.omni.financia.services.impl;

import io.omni.financia.domains.Transaction;
import io.omni.financia.dto.TransactionDto;
import io.omni.financia.repository.TransactionRepo;
import io.omni.financia.services.TransactionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private @Resource TransactionRepo transactionRepo;

    @Override
    public List<Transaction> getAll(Long id) {
        return transactionRepo.findTransactionByLedgerId(id);
    }

    @Override
    public Transaction addTransaction(TransactionDto transactionDto) {
        return transactionRepo.save(transactionDto.toEntity());
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepo.deleteById(id);
    }
}
