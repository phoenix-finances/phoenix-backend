package io.omni.financia.repository;

import io.omni.financia.domains.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> getAppUserById(Long userId);
    public Optional<AppUser> findByEmail(String email);
    public AppUser findAppUserByEmail(String email);
    //Optional<AppUser> getAppUserByUserPost(Long postId);


}
