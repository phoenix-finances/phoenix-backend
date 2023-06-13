package io.omni.financia.services;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.dto.AppUserRegistrationDto;

import java.util.List;

public interface AppUserService {

    AppUser getUser(Long userId);
    List<AppUser> getAllUsers();

    AppUser insert(AppUser request);

    AppUser update(AppUser request);

    List<AppUser> getByBusiness(Long businessId);

    AppUser registerUser(AppUserRegistrationDto registrationDto);
}
