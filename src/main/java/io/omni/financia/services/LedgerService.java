package io.omni.financia.services;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Ledger;
import io.omni.financia.dto.LedgerDto;

import java.security.Principal;
import java.util.List;

public interface LedgerService {
    List<LedgerDto> getAllLedger(AppUser user);

    Ledger getUserLedger(Long id);

    void deleteLedger();

    Ledger updateLedger(LedgerDto data);

    Ledger addLedger(Ledger data, AppUser createdBy);
}
