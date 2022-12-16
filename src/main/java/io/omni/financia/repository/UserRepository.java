package io.omni.financia.repository;

import io.omni.financia.domains.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

}
