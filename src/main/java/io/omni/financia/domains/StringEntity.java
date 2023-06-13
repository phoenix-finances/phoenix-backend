package io.omni.financia.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter @Setter
@MappedSuperclass
public class StringEntity extends NumberEntity {
    private String name;
}
