package io.omni.financia.services.impl;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.domains.TransactionTimeline;
import io.omni.financia.repository.TransactionGroupRepository;
import io.omni.financia.repository.TransactionRepository;
import io.omni.financia.repository.TransactionTimelineRepository;
import io.omni.financia.services.TransactionGroupService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionGroupServiceImpl implements TransactionGroupService {
    private @Resource TransactionGroupRepository transactionGroupRepository;
    private @Resource TransactionRepository transactionRepository;
    private @Resource TransactionTimelineRepository transactionTimelineRepository;

    @Override
    public List<TransactionGroup> getGroupsByTimelineId(Long id) {
        return transactionGroupRepository.getTransactionGroupsByTransactionTimelineId(id);
    }

    @Override
    public TransactionGroup addTransaction(TransactionGroup transactionGroup, AppUser createdBy) {
        TransactionTimeline timeline = transactionTimelineRepository
                .findTransactionTimelineByOwnerId(createdBy.getId())
                .orElseThrow();

        List<Transaction> transactions = transactionGroup.getTransactions();
        transactionGroup.setTransactions(null);

        transactionGroup.setTransactionTimeline(timeline);
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
