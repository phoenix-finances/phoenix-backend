package io.omni.financia.services.impl;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.TransactionTimeline;
import io.omni.financia.dto.AppUserRegistrationDto;
import io.omni.financia.repository.TransactionTimelineRepository;
import io.omni.financia.repository.UserRepository;
import io.omni.financia.services.AppUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private @Resource UserRepository userRepository;
    private @Resource TransactionTimelineRepository transactionTimeLineRepository;

    @Override
    public AppUser getUser(Long userId) {
        // TODO throw service exception or not found exception
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser insert(AppUser request) {
        TransactionTimeline transactionTimeline = new TransactionTimeline();
        AppUser savedUser=userRepository.save(request);
        transactionTimeline.setOwner(savedUser);
        transactionTimeLineRepository.save(transactionTimeline);
        return savedUser;
    }

    @Override
    public TransactionTimeline getUserTransactionTimeline(Long userId) {
        return transactionTimeLineRepository.findTransactionTimelineByOwnerId(userId)
                .orElseThrow();
    }

    @Override
    public AppUser update(AppUser request) {
        return null;
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return userRepository.findAppUserByEmail(email);
    }

    @Override
    public AppUser registerUser(AppUserRegistrationDto registrationDto) {
        AppUser request = new AppUser();
        request.setName(registrationDto.getName());

        return null;
    }

}
