package io.omni.financia.controllers.UIcontrollers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.dto.LoginRequest;
import io.omni.financia.dto.LoginResponse;
import io.omni.financia.security.JwtHelper;
import io.omni.financia.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/signin")
public class LoginUIcontroller {
    private @Resource AppUserService appUserService;
    private @Resource AuthenticationManager manager;
    private @Resource UserDetailsService userDetailsService;
    private @Resource JwtHelper helper;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String signin(@ModelAttribute LoginRequest request, Model model) {
        AppUser user = appUserService.findUserByEmail(request.getEmail());
        if (user == null)
            model.addAttribute("notFoundMsg", "User Not Found");
        if (user != null) {
            doAuthenticate(user.getId().toString(), request.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getId().toString());
            String token = helper.generateToken(userDetails);

            LoginResponse response = LoginResponse.builder()
                    .jwtToken(token)
                    .username(userDetails.getUsername()).build();
            return "redirect:/";
        }
        return "login";
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
