package io.omni.financia.controllers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.dto.AppUserDto;
import io.omni.financia.dto.LoginRequest;
import io.omni.financia.dto.LoginResponse;
import io.omni.financia.repository.UserRepository;
import io.omni.financia.security.JwtHelper;
import io.omni.financia.services.AppUserService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private @Resource UserDetailsService userDetailsService;
    private @Resource UserRepository userRepository;
    private @Resource AuthenticationManager manager;
    private @Resource PasswordEncoder passwordEncoder;
    private @Resource JwtHelper helper;
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
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            logger.info("Duplicate Email Not Acceptable!");
            respone.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return new AppUser();
        } else {
            request.setPassword((passwordEncoder.encode(request.getPassword())));
            return appUserService.insert(request);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        AppUser user = appUserService.findUserByEmail(request.getEmail());
        if (user == null)
            return ResponseEntity.badRequest().build();

        doAuthenticate(user.getId().toString(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getId().toString());
        String token = helper.generateToken(userDetails);

        LoginResponse response = LoginResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public AppUser getMyself(Principal principal) {
        logger.info("kkkkkkkkkkkkkkkkk" + principal.getName());
        return userRepository.findById(Long.valueOf((principal.getName()))).orElse(null);
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
