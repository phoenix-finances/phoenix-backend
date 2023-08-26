package io.omni.financia.controllers;

import io.omni.financia.domains.Transaction;
import io.omni.financia.dto.TransactionDto;
import io.omni.financia.services.UnitTransactionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private @Resource UnitTransactionService unitTransactionService;

    @GetMapping("/transaction/{id}")
    List<TransactionDto> getOfTransaction(@PathVariable Long id) {
        return unitTransactionService.getUnitTransactionOfTransaction(id);
    }

    @GetMapping("/ledger/{id}")
    List<TransactionDto> getOfLedger(@PathVariable Long id) {
        return unitTransactionService.getUnitTransactionOfLedger(id);
    }

    @PostMapping
    TransactionDto createUnitTransaction(@RequestBody Transaction data){
        return unitTransactionService.createUnitTransaction(data).toDto();
    }

    @DeleteMapping("/unitTransactionId")
    void delete(@PathVariable Long unitTransactionId) {
        unitTransactionService.deleteUnitTransaction(unitTransactionId);
    }
}
