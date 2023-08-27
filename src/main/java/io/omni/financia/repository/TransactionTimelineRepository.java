package io.omni.financia.repository;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.TransactionTimeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionTimelineRepository extends JpaRepository<TransactionTimeline,Long> {
    Optional<TransactionTimeline> findTransactionTimelineByOwnerId(Long userId);
}
