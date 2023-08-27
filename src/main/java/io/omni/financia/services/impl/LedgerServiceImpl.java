package io.omni.financia.services.impl;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.LedgerPermission;
import io.omni.financia.dto.LedgerDto;
import io.omni.financia.repository.LedgerPermissionRepository;
import io.omni.financia.repository.LedgerRepository;
import io.omni.financia.services.LedgerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LedgerServiceImpl implements LedgerService {
    private @Resource LedgerRepository ledgerRepository;
    private @Resource LedgerPermissionRepository ledgerPermissionRepository;

    @Override
    public List<LedgerDto> getAllLedger(AppUser user) {
        List<LedgerDto> ledgerDtos = new ArrayList<>();
        List<LedgerPermission> permissions = ledgerPermissionRepository.findLedgerPermissionsByUserId(user.getId());

        for (LedgerPermission permission : permissions) {
            ledgerDtos.add(permission.getLedger().toDto());
        }
        return ledgerDtos;
    }

    @Override
    public Ledger getUserLedger(Long id) {
        return ledgerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteLedger() {

    }

    @Override
    public Ledger updateLedger(LedgerDto data) {
        return null;
    }

    @Override
    public Ledger addLedger(Ledger data, AppUser appUser) {

        Ledger savedLedger = ledgerRepository.save(data);

        LedgerPermission permission = new LedgerPermission();
        permission.setPermission(LedgerPermission.Permission.OWNER);
        permission.setLedger(savedLedger);
        permission.setUser(appUser);
        ledgerPermissionRepository.save(permission);

        return savedLedger;
    }
}
