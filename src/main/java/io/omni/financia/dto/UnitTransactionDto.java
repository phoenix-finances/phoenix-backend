package io.omni.financia.dto;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.UnitTransaction;
import lombok.Data;

@Data
public class UnitTransactionDto {
    private TransactionDto transaction;
    private LedgerDto ledger;
    private double debit;
    private double credit;

    public UnitTransaction toEntity() {
        UnitTransaction entity = new UnitTransaction();
        entity.setTransaction(new Transaction(transaction.getId()));
        entity.setCredit(credit);
        entity.setDebit(debit);
        entity.setLedger(new Ledger(ledger.getId()));
        return entity;
    }

    public static UnitTransactionDto from(UnitTransaction entity) {
        UnitTransactionDto dto = new UnitTransactionDto();
        dto.setTransaction(entity.getTransaction().toDto());
        dto.setDebit(entity.getDebit());
        dto.setLedger(entity.getLedger().toDto());
        dto.setCredit(entity.getCredit());
        return dto;
    }
}
