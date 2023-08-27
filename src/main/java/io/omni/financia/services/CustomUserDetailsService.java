package io.omni.financia.services;

import io.omni.financia.domains.AppUser;
import io.omni.financia.repository.UserRepository;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private @Resource UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Requested UserDetails for username: {}", username);
        AppUser user = userRepository.findById(Long.valueOf((username))).orElseThrow(()->new RuntimeException("USER NOT FOUND"));
        return new User(String.valueOf(user.getId()), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}