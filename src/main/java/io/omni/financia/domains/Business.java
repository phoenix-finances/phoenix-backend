package io.omni.financia.domains;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "businesses")
@Data
public class Business {

    @SequenceGenerator(name = "seq_biz_gen", sequenceName = "seq_biz_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_biz_gen", strategy = GenerationType.SEQUENCE)
    private @Id Long id;

    private String name;

    public Business(){}
    public Business(Long id){this.id = id;}
}
