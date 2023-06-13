package io.omni.financia.controllers;

import io.omni.financia.domains.Business;
import io.omni.financia.domains.dto.AppUserDto;
import io.omni.financia.repository.BusinessRepository;
import io.omni.financia.repository.UserRepository;
import io.omni.financia.services.BusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    private @Resource BusinessRepository businessRepository;
    private @Resource BusinessService businessService;
    private @Resource UserRepository userRepository;

    @PostMapping
    public Business create(@RequestBody Business business){
        return businessService.create(business);
    }

    @GetMapping
    public Iterable<Business> getAll(){
        return businessRepository.findAll();
    }

    @GetMapping("/{businessId}/users")
    public List<AppUserDto> getBusinessUsers(@PathVariable Long businessId){
        return userRepository.getAppUsersByBusiness_Id(businessId)
                .stream().map(AppUserDto::from)
                .collect(Collectors.toList());
    }
}
