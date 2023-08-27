package io.omni.financia.dto;

import io.omni.financia.domains.Ledger;
import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.domains.TransactionTimeline;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionGroupDto {
    private Long id;
    private String description;
    private List<TransactionDto> transactions;
    private TransactionTimelineDto transactionTimeline;
//    private String dateTime;

    public TransactionGroup toEntity() {
        TransactionGroup transaction = new TransactionGroup();
        transaction.setId(id);
        transaction.setDescription(description);
        transaction.setTransactionTimeline(new TransactionTimeline(transactionTimeline.getId()));
        if (transactions != null) {
            List<Transaction> transactionEntities = new ArrayList<>();
            for (TransactionDto transactionDto : transactions) {
                transactionEntities.add(transactionDto.toEntity());
            }
            transaction.setTransactions(transactionEntities);
        }
        //transaction.setLedger(new Ledger(ledger.getId()));
        return transaction;
    }

    public static TransactionGroupDto from(TransactionGroup entity) {
        TransactionGroupDto dto = new TransactionGroupDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setTransactionTimeline(entity.getTransactionTimeline().toDto());
        if (entity.getTransactions() != null) {
            List<TransactionDto> transactionDtos = new ArrayList<>();

            for (Transaction transaction : entity.getTransactions()) {
                transactionDtos.add(transaction.toDto());
            }
            dto.setTransactions(transactionDtos);
        }
        //dto.setLedger(entity.getLedger().toDto());
        return dto;
    }
}
