package io.omni.financia.dto;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.domains.Transaction;
import lombok.Data;

@Data
public class TransactionDto {
    private Long transactionGroupId;
    private LedgerDto ledger;
    private double debit;
    private double credit;

    public Transaction toEntity() {
        Transaction entity = new Transaction();
        /*if (transactionGroup != null)
            entity.setTransactionGroup(new TransactionGroup(transactionGroup.getId()));*/

        entity.setCredit(credit);
        entity.setDebit(debit);
        entity.setLedger(new Ledger(ledger.getId()));
        return entity;
    }

    public static TransactionDto from(Transaction entity) {
        TransactionDto dto = new TransactionDto();
        /*if (entity.getTransactionGroup() != null)
            dto.setTransactionGroup(entity.getTransactionGroup().toDto());*/
        dto.setTransactionGroupId(entity.getTransactionGroup().getId());

        dto.setDebit(entity.getDebit());
        dto.setLedger(entity.getLedger().toDto());
        dto.setCredit(entity.getCredit());
        return dto;
    }
}
