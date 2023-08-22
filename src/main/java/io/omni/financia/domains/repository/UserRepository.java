package io.omni.financia.domains.repository;

import io.omni.financia.domains.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> getAppUserById(Long userId);
    public Optional<AppUser> findByEmail(String email);
    public AppUser findAppUserByEmail(String email);
}
