package io.omni.financia.controllers;

import io.omni.financia.domains.UnitTransaction;
import io.omni.financia.dto.UnitTransactionDto;
import io.omni.financia.services.UnitTransactionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unitTransactions")
public class UnitTransactionController {
    private @Resource UnitTransactionService unitTransactionService;

    @GetMapping("/transaction/{id}")
    List<UnitTransactionDto> getOfTransaction(@PathVariable Long id) {
        return unitTransactionService.getUnitTransactionOfTransaction(id);
    }

    @GetMapping("/ledger/{id}")
    List<UnitTransactionDto> getOfLedger(@PathVariable Long id) {
        return unitTransactionService.getUnitTransactionOfLedger(id);
    }

    @PostMapping
    UnitTransactionDto createUnitTransaction(@RequestBody UnitTransaction data){
        return unitTransactionService.createUnitTransaction(data).toDto();
    }

    @DeleteMapping("/unitTransactionId")
    void delete(@PathVariable Long unitTransactionId) {
        unitTransactionService.deleteUnitTransaction(unitTransactionId);
    }
}
