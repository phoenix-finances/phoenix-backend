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
public class TransactionServiceImpl implements TransactionService {
    private @Resource TransactionRepository transactionRepository;

    @Override
    public List<TransactionDto> getTransaction() {
        List<Transaction>entity=new ArrayList<>();
        entity=transactionRepository.findAll();
        List<TransactionDto>dto=new ArrayList<>();
        for(Transaction elm: entity){
            dto.add(elm.toDto());
        }
        return dto;
    }

    @Override
    public List<TransactionDto> getTransactionOfTransactionGroup(Long transactionGroupId) {
        List<Transaction> entity = new ArrayList<>();
        List<TransactionDto> dto = new ArrayList<>();
        entity = transactionRepository.findTransactionByTransactionGroupId(transactionGroupId);
        for (Transaction elm : entity) {
            dto.add(elm.toDto());
        }
        return dto;
    }

    @Override
    public List<TransactionDto> getUnitTransactionOfLedger(Long ledgerId) {
        List<Transaction> entity = new ArrayList<>();
        List<TransactionDto> dto = new ArrayList<>();
        entity = transactionRepository.findTransactionByLedgerId(ledgerId);
        for (Transaction elm : entity) {
            dto.add(elm.toDto());
        }
        return dto;
    }

    @Override
    public Transaction createUnitTransaction(Transaction data) {
        return transactionRepository.save(data);
    }

    @Override
    public void deleteUnitTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
