package io.omni.financia.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.omni.financia.config.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    private @Resource JwtHelper jwtHelper;
    private @Resource UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        Optional<String> tokenHolder = HttpUtils.getUserTokenCookie(request);
        if (tokenHolder.isPresent()){
            authenticate(request, response, tokenHolder.get());
        } else if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            String token = requestHeader.substring(7);
            authenticate(request, response, token);
        } else {
            // try with URL for future purposes
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response, String token){
        String username = null;
        try {
            username = this.jwtHelper.getUsernameFromToken(token);
        } catch (IllegalArgumentException e) {
            logger.warn("Illegal Argument while fetching the username !!");
        } catch (ExpiredJwtException e) {
            logger.warn("JWT Token Expired: {}", token);
        } catch (MalformedJwtException e) {
            logger.warn("MalformedJwt: {}", token);
        } catch (Exception e) {
            // e.printStackTrace();
            logger.warn("{} : {}", e.getClass().getName(), e.getMessage());
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //fetch user detail from username
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Boolean validateToken = jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("Validation fails !!");
            }
        }
    }

}
