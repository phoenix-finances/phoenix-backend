package io.omni.financia.repository;

import io.omni.financia.domains.TransactionTimeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTimeLineRepository extends JpaRepository<TransactionTimeline,Long> {
}
