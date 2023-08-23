package io.omni.financia.domains;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    @SequenceGenerator(name = "seq_generator", sequenceName = "phoenix_sequence", initialValue=999)
    protected Long id;

    public AbstractEntity(){}

    public AbstractEntity(Long id){this.id = id;}
}
