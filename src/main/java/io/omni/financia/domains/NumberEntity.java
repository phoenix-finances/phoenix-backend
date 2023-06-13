package io.omni.financia.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class NumberEntity {
    private Long number;
}
