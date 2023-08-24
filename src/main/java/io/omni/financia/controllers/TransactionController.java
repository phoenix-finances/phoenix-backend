package io.omni.financia.controllers;

import io.omni.financia.domains.Transaction;
import io.omni.financia.services.TransactionService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Resource
    TransactionService transactionService;

    @GetMapping("/{ledgerId}")
    List<Transaction> getTransactionByLedger(@PathVariable long ledgerId) {
        return transactionService.getAll(ledgerId);
    }
    @PostMapping
    Transaction createTransaction(@RequestBody Transaction data){
        return transactionService.addTransaction(data.toDto());
    }

    @DeleteMapping("/transactionId")
    void delete(@PathVariable long transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
