package io.omni.financia.config;

import io.omni.financia.security.JwtAuthenticationEntryPoint;
import io.omni.financia.security.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    private UserDetailsService userDetailService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtAuthenticationEntryPoint point;
    @Resource
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth ->
                        auth.requestMatchers("/posts").authenticated()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/users").permitAll()
                                .requestMatchers("/ledgers").permitAll()
                                .requestMatchers("/ledgers/**").permitAll()
                                .requestMatchers("/users/login").permitAll()
                                .requestMatchers("/transactions").permitAll()
                                .requestMatchers("/transactions/**").permitAll()
                                //.authenticated().requestMatchers("/customer").permitAll()
                                .anyRequest().permitAll())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
