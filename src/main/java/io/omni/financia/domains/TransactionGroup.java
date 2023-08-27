package io.omni.financia.domains;

import io.omni.financia.dto.TransactionGroupDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_groups")

public class TransactionGroup extends AbstractEntity {
    private String description;

    @OneToMany
    private List<Transaction> transactions;

    @ManyToOne
    private TransactionTimeline transactionTimeline;



    public TransactionGroup(Long id) {
        super(id);
    }

/*    @Transient
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
   private String dateTime = LocalDateTime.now().format(myFormatObj);*/

    public TransactionGroupDto toDto() {
        return TransactionGroupDto.from(this);
    }


}
