package io.omni.financia.controllers;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.repository.LedgerRepo;
import io.omni.financia.domains.repository.UserRepository;
import io.omni.financia.services.LedgerService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/ledgers")
public class LedgerController {
    @Autowired
    private LedgerService ledgerService;
    private @Resource UserRepository userRepository;

    @GetMapping
    List<Ledger> getAll() {
        return ledgerService.getAllLedger();
    }
    @GetMapping("/{id}")
    Ledger getOne(@PathVariable Long id){
        return ledgerService.getUserLedger(id);
    }

    @PostMapping
    Ledger add(@RequestBody Ledger data, Principal principal){
        data.setAppUser(userRepository.findAppUserByEmail(principal.getName()));
        return ledgerService.addLedger(data.toDto());
    }
}
