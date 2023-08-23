package io.omni.financia.domains;

import io.omni.financia.domains.dto.TransactionDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    @OneToMany
    private List<UnitTransaction> unitTransaction;
/*    @Transient
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");*/
//    private String dateTime = LocalDateTime.now().format(myFormatObj);

    public TransactionDto toDto() {
        return Transaction.from(this);
    }

    private static TransactionDto from(Transaction entity) {
        TransactionDto dto = new TransactionDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
//        dto.setDateTime(entity.getDateTime());
        dto.setUnitTransaction(entity.getUnitTransaction());
        return dto;
    }
}
