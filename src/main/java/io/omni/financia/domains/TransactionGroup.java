package io.omni.financia.domains;

import io.omni.financia.dto.TransactionGroupDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data @NoArgsConstructor
@Table(name = "transaction_groups")
public class TransactionGroup extends AbstractEntity {
    private String description;

    @OneToMany(mappedBy = "transactionGroup")
    private List<Transaction> transactions;

    @ManyToOne
    private TransactionTimeline transactionTimeline;

    public TransactionGroup(Long id) {
        super(id);
    }

    /*@Transient
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    private String dateTime = LocalDateTime.now().format(myFormatObj);*/

    public TransactionGroupDto toDto() {
        return TransactionGroupDto.from(this);
    }


}
