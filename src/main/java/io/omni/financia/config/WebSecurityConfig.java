package io.omni.financia.config;

import io.omni.financia.security.JwtAuthenticationEntryPoint;
import io.omni.financia.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

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
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/posts").authenticated()
                        .antMatchers("/").permitAll()
                        .antMatchers("/signup").permitAll()
                        .antMatchers("/signin").permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers(HttpMethod.POST, "/users").permitAll()
                        //.requestMatchers("/ledgers").permitAll()
                        //.requestMatchers("/ledgers/**").permitAll()
                        .antMatchers("/users/login").permitAll()
                        //.requestMatchers("/transactions").permitAll()
                        //.requestMatchers("/transactions/**").permitAll()
                        //.authenticated().requestMatchers("/customer").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
