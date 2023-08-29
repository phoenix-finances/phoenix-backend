package io.omni.financia.controllers;

import io.omni.financia.domains.Transaction;
import io.omni.financia.dto.TransactionDto;
import io.omni.financia.repository.TransactionRepository;
import io.omni.financia.services.TransactionService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private @Resource TransactionService transactionService;

    @GetMapping("/transaction-group/{id}")
    List<TransactionDto> getOfTransactionGroup(@PathVariable Long id) {
        return transactionService.getTransactionOfTransactionGroup(id);
    }

    @GetMapping("/ledger/{id}")
    List<TransactionDto> getOfLedger(@PathVariable Long id) {
        return transactionService.getUnitTransactionOfLedger(id);
    }

    @PostMapping
    TransactionDto createUnitTransaction(@RequestBody Transaction data){
        return transactionService.createUnitTransaction(data).toDto();
    }

    @DeleteMapping("/unitTransactionId")
    void delete(@PathVariable Long unitTransactionId) {
        transactionService.deleteUnitTransaction(unitTransactionId);
    }

    private @Resource TransactionRepository transactionRepository;
    //TEST
    @GetMapping
    List<TransactionDto>transactionDtos(){
        return transactionService.getTransaction();
    }
}
