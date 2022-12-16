package io.omni.financia.services.impl;

import io.omni.financia.domains.AppUser;
import io.omni.financia.repository.UserRepository;
import io.omni.financia.services.AppUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private @Resource UserRepository userRepository;

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser insert(AppUser request) {
        return userRepository.save(request);
    }

    @Override
    public AppUser update(AppUser request) {
        return null;
    }
}
