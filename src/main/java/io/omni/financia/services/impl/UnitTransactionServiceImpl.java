package io.omni.financia.services.impl;

import io.omni.financia.domains.Transaction;
import io.omni.financia.dto.TransactionDto;
import io.omni.financia.repository.TransactionRepository;
import io.omni.financia.services.TransactionService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitTransactionServiceImpl implements TransactionService {
    private @Resource TransactionRepository unitTransactionRepo;

    @Override
    public List<TransactionDto> getUnitTransactionOfTransaction(Long transactionId) {
        List<Transaction> entity = new ArrayList<>();
        List<TransactionDto> dto = new ArrayList<>();
        entity = unitTransactionRepo.findTransactionsByTransactionGroupId(transactionId);
        for (Transaction elm : entity) {
            dto.add(elm.toDto());
        }
        return dto;
    }

    @Override
    public List<TransactionDto> getUnitTransactionOfLedger(Long ledgerId) {
        List<Transaction> entity = new ArrayList<>();
        List<TransactionDto> dto = new ArrayList<>();
        entity = unitTransactionRepo.findTransactionsByTransactionGroupId(ledgerId);
        for (Transaction elm : entity) {
            dto.add(elm.toDto());
        }
        return dto;
    }

    @Override
    public Transaction createUnitTransaction(Transaction data) {
        return unitTransactionRepo.save(data);
    }

    @Override
    public void deleteUnitTransaction(Long id) {
        unitTransactionRepo.deleteById(id);
    }
}
