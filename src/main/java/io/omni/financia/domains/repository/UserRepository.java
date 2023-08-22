package io.omni.financia.domains.repository;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> getAppUserById(Long userId);
    public Optional<AppUser> findByEmail(String email);
    public AppUser findAppUserByEmail(String email);
    //Optional<AppUser> getAppUserByUserPost(Long postId);

    List<AppUser> getAppUsersByBusiness(Business business);
    List<AppUser> getAppUsersByBusiness_Id(Long businessId);
}
