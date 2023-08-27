package io.omni.financia.controllers;

import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.dto.TransactionGroupDto;
import io.omni.financia.services.TransactionGroupService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-groups")
public class TransactionGroupController {
    @Resource
    TransactionGroupService transactionGroupService;

    @GetMapping("/{ledgerId}")
    List<TransactionGroup> getTransactionByLedger(@PathVariable long ledgerId) {
        return transactionGroupService.getAll(ledgerId);
    }
    @PostMapping
    TransactionGroupDto createTransaction(@RequestBody TransactionGroupDto data){
        return transactionGroupService.addTransaction(data.toEntity())
                .toDto();
    }

    @DeleteMapping("/transactionId")
    void delete(@PathVariable long transactionId){
        transactionGroupService.deleteTransaction(transactionId);
    }
}
