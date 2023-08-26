package io.omni.financia.repository;

import io.omni.financia.domains.LedgerPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerPermissionRepository extends JpaRepository<LedgerPermission,Long> {
}
