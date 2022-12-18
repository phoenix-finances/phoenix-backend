package io.omni.financia._2_integration_tests;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SecurityTestConfig {
    // https://stackoverflow.com/questions/15203485/spring-test-security-how-to-mock-authentication

    // Additional explanations (or code samples) for spring-test-security
    // https://www.baeldung.com/spring-security-integration-tests

    // How to disable security during testing
    // https://stackoverflow.com/questions/47593537/disable-spring-security-config-class-for-webmvctest-in-spring-boot

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User("user_1", "password",
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("PERM_FOO_READ")
                ));

        User managerUser = new User("user_2",  "password",
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_MANAGER"),
                        new SimpleGrantedAuthority("PERM_FOO_READ"),
                        new SimpleGrantedAuthority("PERM_FOO_WRITE"),
                        new SimpleGrantedAuthority("PERM_FOO_MANAGE")
                ));

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUser, managerUser
        ));
    }
}
