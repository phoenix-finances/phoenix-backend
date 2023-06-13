package io.omni.financia.repository;

import io.omni.financia.domains.Business;
import org.springframework.data.repository.CrudRepository;

public interface BusinessRepository extends CrudRepository<Business, Long> {
}
