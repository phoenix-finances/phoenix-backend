package io.omni.financia.services.impl;

import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.UnitTransaction;
import io.omni.financia.dto.TransactionDto;
import io.omni.financia.dto.UnitTransactionDto;
import io.omni.financia.repository.UnitTransactionRepo;
import io.omni.financia.services.TransactionService;
import io.omni.financia.services.UnitTransactionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitTransactionServiceImpl implements UnitTransactionService {
    private @Resource UnitTransactionRepo unitTransactionRepo;

    @Override
    public List<UnitTransactionDto> getUnitTransactionOfTransaction(Long transactionId) {
        List<UnitTransaction> entity = new ArrayList<>();
        List<UnitTransactionDto> dto = new ArrayList<>();
        entity = unitTransactionRepo.findUnitTransactionByTransactionId(transactionId);
        for (UnitTransaction elm : entity) {
            dto.add(elm.toDto());
        }
        return dto;
    }

    @Override
    public List<UnitTransactionDto> getUnitTransactionOfLedger(Long ledgerId) {
        List<UnitTransaction> entity = new ArrayList<>();
        List<UnitTransactionDto> dto = new ArrayList<>();
        entity = unitTransactionRepo.findUnitTransactionByTransactionId(ledgerId);
        for (UnitTransaction elm : entity) {
            dto.add(elm.toDto());
        }
        return dto;
    }

    @Override
    public UnitTransaction createUnitTransaction(UnitTransaction data) {
        return unitTransactionRepo.save(data);
    }

    @Override
    public void deleteUnitTransaction(Long id) {
        unitTransactionRepo.deleteById(id);
    }
}
