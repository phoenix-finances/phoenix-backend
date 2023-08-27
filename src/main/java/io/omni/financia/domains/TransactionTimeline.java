package io.omni.financia.domains;

import io.omni.financia.dto.TransactionDto;
import io.omni.financia.dto.TransactionTimelineDto;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_timelines")
public class TransactionTimeline extends AbstractEntity {
    @OneToOne
    private AppUser owner;

    public TransactionTimeline(Long id) {
        super(id);
    }
    public TransactionTimelineDto toDto(){
        return TransactionTimelineDto.from(this);
}
}
