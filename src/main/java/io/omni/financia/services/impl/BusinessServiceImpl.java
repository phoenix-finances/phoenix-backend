package io.omni.financia.services.impl;

import io.omni.financia.domains.Business;
import io.omni.financia.repository.BusinessRepository;
import io.omni.financia.services.BusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessServiceImpl implements BusinessService {

    private @Resource BusinessRepository businessRepository;

    @Override
    public Business create(Business business) {
        return businessRepository.save(business);
    }
}
