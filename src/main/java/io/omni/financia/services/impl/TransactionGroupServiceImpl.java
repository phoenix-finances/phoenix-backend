package io.omni.financia.services.impl;

import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.repository.TransactionGroupRepository;
import io.omni.financia.repository.TransactionRepository;
import io.omni.financia.services.TransactionGroupService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionGroupServiceImpl implements TransactionGroupService {
    private @Resource TransactionGroupRepository transactionGroupRepository;
    private @Resource TransactionRepository transactionRepository;

    @Override
    public List<TransactionGroup> getAll(Long id) {
        //return transactionGroupRepository.findTransactionByLedgerId(id);
        return new ArrayList<>();
    }

    @Override
    public TransactionGroup addTransaction(TransactionGroup transactionGroup) {
        List<Transaction> transactions = transactionGroup.getTransactions();
        transactionGroup.setTransactions(null);

        TransactionGroup savedGroup = transactionGroupRepository.save(transactionGroup);

        List<Transaction> savedTransactions = new ArrayList<>();

        for (Transaction transaction : transactions){
            transaction.setTransactionGroup(savedGroup);
            savedTransactions.add(transactionRepository.save(transaction));
        }

        savedGroup.setTransactions(savedTransactions);

        return savedGroup;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionGroupRepository.deleteById(id);
    }
}
