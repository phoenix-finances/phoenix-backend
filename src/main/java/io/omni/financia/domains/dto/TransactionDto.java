package io.omni.financia.domains.dto;

import io.omni.financia.domains.Transaction;
import io.omni.financia.domains.UnitTransaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransactionDto {
    private Long id;
    private String description;
    private List<UnitTransaction> unitTransaction;
//    private String dateTime;

//   public Transaction toEntity(){
//       Transaction transaction=new Transaction();
//       transaction.setId(id);
//       transaction.setDescription(description);
////       transaction.setUnitTransaction(new UnitTransaction());
//   }
}
