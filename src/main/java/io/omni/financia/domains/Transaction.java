package io.omni.financia.domains;

import io.omni.financia.dto.TransactionDto;
import io.omni.financia.dto.UnitTransactionDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")

public class Transaction extends AbstractEntity {
    private String description;
    @OneToMany
    private List<UnitTransaction> unitTransaction;
    @ManyToOne
    private Ledger ledger;

    public Transaction(Long id) {
        super(id);
    }

/*    @Transient
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
   private String dateTime = LocalDateTime.now().format(myFormatObj);*/

    public TransactionDto toDto() {
        return Transaction.from(this);
    }

    private static TransactionDto from(Transaction entity) {
        TransactionDto dto = new TransactionDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
//        dto.setDateTime(entity.getDateTime());
        List<UnitTransactionDto> trnsDto = new ArrayList<>();
        List<UnitTransaction> trnsEnt = new ArrayList<>();
        trnsEnt = entity.getUnitTransaction();
        for (UnitTransaction elm : trnsEnt) {
            trnsDto.add(elm.toDto());
        }
        dto.setUnitTransactions(trnsDto);
        dto.setLedger(entity.getLedger().toDto());
        return dto;
    }
}
