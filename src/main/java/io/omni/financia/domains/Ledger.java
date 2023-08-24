package io.omni.financia.domains;

import io.omni.financia.dto.LedgerDto;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@Table(name = "ledgers")
public class Ledger extends AbstractEntity {
    private String name;
    private double balance;
    private int transactionCount = 0;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Ledger parent;

    public Ledger() {

    }

    public Ledger(Long id) {
        super(id);
    }

    public LedgerDto toDto() {
        return Ledger.from(this);
    }

    private static LedgerDto from(Ledger entity) {
        LedgerDto dto = new LedgerDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBalance(entity.getBalance());
        dto.setTransactionCount(entity.getTransactionCount());
        dto.setAppUser(entity.getAppUser());
        return dto;
    }

}
