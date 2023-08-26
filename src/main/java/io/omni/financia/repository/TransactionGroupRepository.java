package io.omni.financia.repository;

import io.omni.financia.domains.TransactionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionGroupRepository extends JpaRepository<TransactionGroup, Long> {
}
