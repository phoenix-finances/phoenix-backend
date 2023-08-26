package io.omni.financia.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ledger_permissions")
public class LedgerPermission {
    private AppUser user;
    private Ledger ledger;
    
    @Enumerated(EnumType.STRING)
    private Permission permission;

    public enum Permission{
        OWNER, WRITE, READ
    }
}
