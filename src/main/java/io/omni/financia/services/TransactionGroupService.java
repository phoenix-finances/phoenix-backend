package io.omni.financia.services;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.TransactionGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionGroupService {
    List<TransactionGroup> getGroupsByTimelineId(Long id);

    TransactionGroup addTransaction(TransactionGroup transactionDto, AppUser createdBy);

    void deleteTransaction(Long id);
}
