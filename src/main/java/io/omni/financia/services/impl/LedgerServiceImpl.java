package io.omni.financia.services.impl;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.dto.LedgerDto;
import io.omni.financia.domains.repository.LedgerRepo;
import io.omni.financia.services.LedgerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LedgerServiceImpl implements LedgerService {
    private @Resource LedgerRepo ledgerRepo;

    @Override
    public List<Ledger> getAllLedger() {
        return ledgerRepo.findAll();
    }

    @Override
    public Ledger getUserLedger(Long id) {
        return ledgerRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteLedger() {

    }

    @Override
    public Ledger updateLedger(LedgerDto data) {
        return null;
    }

    @Override
    public Ledger addLedger(LedgerDto data) {
        return ledgerRepo.save(data.toEntity());
    }
}
