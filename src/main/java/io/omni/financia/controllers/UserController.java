package io.omni.financia.controllers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.services.AppUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController{

    private @Resource AppUserService appUserService;

    @GetMapping
    public List<AppUser> getAll(){
        return appUserService.getAllUsers();
    }

    @PostMapping
    public AppUser insert(@RequestBody AppUser request){
        return appUserService.insert(request);
    }
}
