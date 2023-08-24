package io.omni.financia.dto;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.UnitTransaction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionDto {
    private Long id;
    private String description;
    private List<UnitTransactionDto> unitTransactions;
    private LedgerDto ledger;
//    private String dateTime;

    public Transaction toEntity() {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setDescription(description);
        List<UnitTransactionDto> trnsDto = new ArrayList<>();
        List<UnitTransaction> trnsEnt = new ArrayList<>();
        trnsDto = unitTransactions;
        for (UnitTransactionDto elm : trnsDto) {
            trnsEnt.add(elm.toEntity());
        }
        transaction.setUnitTransaction(trnsEnt);
        transaction.setLedger(new Ledger(ledger.getId()));
        return transaction;
    }
}
