package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping({"/", "/login"})
    public String getLogin() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            User loggedInUser = userService.findByUsername(userDetails.getUsername());
            String role = loggedInUser.getRoles().iterator().next().getName();
            role = role.substring(5).toLowerCase(Locale.ROOT);
            return "redirect:/" + role;
        }
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/403";
    }
}