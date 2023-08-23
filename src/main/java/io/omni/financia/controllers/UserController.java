package io.omni.financia.controllers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.JwtRequest;
import io.omni.financia.domains.JwtResponse;
import io.omni.financia.domains.dto.AppUserDto;
import io.omni.financia.domains.repository.UserRepository;
import io.omni.financia.security.JwtHelper;
import io.omni.financia.services.AppUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private @Resource UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AuthenticationManager manager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtHelper helper;

    private @Resource AppUserService appUserService;

    @GetMapping
    public List<AppUserDto> getAll() {
        return appUserService.getAllUsers()
                .stream().map(appUser -> AppUserDto.builder()
                        .id(appUser.getId())
                        .name(appUser.getName())
                        .email(appUser.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping
    public AppUser insert(HttpServletResponse respone, @RequestBody AppUser request) {
        //logger.info("MMMMMMMMMMMMMMMMMMMM"+request.getEmail());
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            logger.info("Duplicate Email Not Acceptable!");
            respone.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return new AppUser();
        } else {
            //logger.info("NNNNNNNNNNNNNNNN"+userRepository.findAppUserByEmail((request.getEmail())).getEmail());
            request.setPassword((passwordEncoder.encode(request.getPassword())));
            return appUserService.insert(request);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public AppUser getMyself(Principal principal){
        logger.info("kkkkkkkkkkkkkkkkk"+principal.getName());

        return userRepository.findAppUserByEmail(principal.getName());
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
}
