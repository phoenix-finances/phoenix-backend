package io.omni.financia.controllers.UIcontrollers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.repository.UserRepository;
import io.omni.financia.services.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/signup")
public class SignupUIcontroller {
    private @Resource UserRepository userRepository;
    private @Resource PasswordEncoder passwordEncoder;
    private @Resource AppUserService appUserService;
    private Logger logger= LoggerFactory.getLogger(SignupUIcontroller.class);

    @GetMapping
    public String signup() {
        return "signup";
    }

    @PostMapping
    public String create(HttpServletResponse response, @ModelAttribute AppUser user , HttpSession session) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.info("Duplicate Email Not Acceptable!");
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            session.setAttribute("errorMsg","The Email Already Registered");
        } else {
            user.setPassword((passwordEncoder.encode(user.getPassword())));
            appUserService.insert(user);
            return "/home";
        }
        return "signup";
    }
}
