package io.omni.financia.dto;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.domains.Transaction;
import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private Long transactionGroupId;
    private Long ledgerId;
    private double debit;
    private double credit;

    public Transaction toEntity() {
        Transaction entity = new Transaction();
        /*if (transactionGroup != null)
            entity.setTransactionGroup(new TransactionGroup(transactionGroup.getId()));*/
        entity.setId(id);
        entity.setCredit(credit);
        entity.setDebit(debit);
        entity.setLedger(new Ledger(ledgerId));
        return entity;
    }

    public static TransactionDto from(Transaction entity) {
        TransactionDto dto = new TransactionDto();
        /*if (entity.getTransactionGroup() != null)
            dto.setTransactionGroup(entity.getTransactionGroup().toDto());*/
        dto.setTransactionGroupId(entity.getTransactionGroup().getId());
        dto.setDebit(entity.getDebit());
        dto.setLedgerId(entity.getLedger().getId());
        dto.setCredit(entity.getCredit());
        dto.setId(entity.getId());
        return dto;
    }
}
