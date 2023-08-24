package io.omni.financia.domains;

import io.omni.financia.dto.UnitTransactionDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity

public class UnitTransaction extends AbstractEntity {
    @ManyToOne
    private Transaction transaction;
    @ManyToOne
    private Ledger ledger;
    private double debit;
    private double credit;

    public UnitTransactionDto toDto() {
        return UnitTransactionDto.from(this);
    }

}
