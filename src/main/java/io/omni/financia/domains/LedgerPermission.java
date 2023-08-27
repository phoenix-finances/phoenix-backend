package io.omni.financia.domains;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="ledger_permissions")
public class LedgerPermission extends AbstractEntity{

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Ledger ledger;

    @Enumerated(EnumType.STRING)
    private Permission permission;

    public enum Permission{
        OWNER, WRITE, READ
    }
}
