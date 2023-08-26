package io.omni.financia.services;

import io.omni.financia.domains.AppUser;
import io.omni.financia.dto.AppUserRegistrationDto;

import java.util.List;

public interface AppUserService {

    AppUser getUser(Long userId);
    List<AppUser> getAllUsers();

    AppUser insert(AppUser request);

    AppUser update(AppUser request);
    AppUser findUserByEmail(String email);

    AppUser registerUser(AppUserRegistrationDto registrationDto);
}
