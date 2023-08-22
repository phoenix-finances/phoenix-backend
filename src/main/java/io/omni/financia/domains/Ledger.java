package io.omni.financia.domains;

import jakarta.persistence.*;

@Entity
public class Ledger {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
    private String name;
    private double Balance;
    private int TransactionCount=0;
    @ManyToOne
    private AppUser appUser;

}
