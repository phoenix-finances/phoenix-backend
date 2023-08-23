package io.omni.financia.domains.dto;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Ledger;
import io.omni.financia.services.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LedgerDto {
    Long id;
    private String name;
    private double balance;
    private int transactionCount = 0;
    private AppUser appUser;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
Logger logger= LoggerFactory.getLogger(LedgerDto.class);

    private Ledger parent;


    public Ledger toEntity() {
        Ledger ledger = new Ledger();
        ledger.setId(id);
        ledger.setName(name);
        ledger.setTransactionCount(transactionCount);
        ledger.setBalance(balance);
        ledger.setAppUser(new AppUser(appUser.getId()));
        return ledger;
    }

}
