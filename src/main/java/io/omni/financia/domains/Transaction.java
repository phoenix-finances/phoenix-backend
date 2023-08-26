package io.omni.financia.domains;

import io.omni.financia.dto.TransactionDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "transactions")

public class Transaction extends AbstractEntity {
    @ManyToOne
    private TransactionGroup transactionGroup;
    @ManyToOne
    private Ledger ledger;
    private double debit;
    private double credit;

    public TransactionDto toDto() {
        return TransactionDto.from(this);
    }

}
