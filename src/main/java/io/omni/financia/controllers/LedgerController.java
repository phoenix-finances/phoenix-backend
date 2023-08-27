package io.omni.financia.controllers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Ledger;
import io.omni.financia.dto.LedgerDto;
import io.omni.financia.repository.UserRepository;
import io.omni.financia.services.AppUserService;
import io.omni.financia.services.LedgerService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/ledgers")
public class LedgerController {
    private @Resource LedgerService ledgerService;
    private @Resource AppUserService userService;

    @GetMapping
    List<LedgerDto> getAll(Principal principal) {
        // TODO return only Ledgers owned by current user
        AppUser user = userService.getUser(Long.parseLong(principal.getName()));
        return ledgerService.getAllLedger(user);
    }

    @GetMapping("/{id}")
    LedgerDto getOne(@PathVariable Long id){
        return ledgerService.getUserLedger(id).toDto();
    }

    @PostMapping
    LedgerDto add(@RequestBody Ledger data, Principal principal){
        AppUser user = userService.getUser(Long.parseLong(principal.getName()));

        //data.setAppUser(user);

        return ledgerService.addLedger(data, user).toDto();
    }
}
