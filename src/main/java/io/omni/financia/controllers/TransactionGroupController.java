package io.omni.financia.controllers;

import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.dto.TransactionGroupDto;
import io.omni.financia.services.TransactionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-groups")
public class TransactionGroupController {
    @Resource
    TransactionService transactionService;

    @GetMapping("/{ledgerId}")
    List<TransactionGroup> getTransactionByLedger(@PathVariable long ledgerId) {
        return transactionService.getAll(ledgerId);
    }
    @PostMapping
    TransactionGroupDto createTransaction(@RequestBody TransactionGroupDto data){
        return transactionService.addTransaction(data.toEntity())
                .toDto();
    }

    @DeleteMapping("/transactionId")
    void delete(@PathVariable long transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
