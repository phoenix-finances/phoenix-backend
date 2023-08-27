package io.omni.financia.dto;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Ledger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LedgerDto {
    private Long id;
    private String name;
    private double balance;
    private int transactionCount = 0;

    private Ledger parent;

    public Ledger toEntity() {
        Ledger ledger = new Ledger();
        ledger.setId(id);
        ledger.setName(name);
        ledger.setTransactionCount(transactionCount);
        ledger.setBalance(balance);
        ledger.setParent(new Ledger(id));
        return ledger;
    }

}
