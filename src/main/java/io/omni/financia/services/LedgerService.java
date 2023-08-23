package io.omni.financia.services;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.dto.LedgerDto;

import java.util.List;

public interface LedgerService {
    List<Ledger> getAllLedger();

    Ledger getUserLedger(Long id);

    void deleteLedger();

    Ledger updateLedger(LedgerDto data);

    Ledger addLedger(LedgerDto data);
}
