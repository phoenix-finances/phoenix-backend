package io.omni.financia.services;

import io.omni.financia.domains.AppUser;

import java.util.List;

public interface AppUserService {
    List<AppUser> getAllUsers();

    AppUser insert(AppUser request);

    AppUser update(AppUser request);
}
