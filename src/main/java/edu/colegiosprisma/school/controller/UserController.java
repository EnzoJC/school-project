package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.service.IUserService;
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
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/login"})
    public String getLogin() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            User loggedInUser = userService.findByUsername(userDetails.getUsername());
            String role = loggedInUser.getRoles().iterator().next().getName();
            // quitar de un String los 4 primeros caracteres
            role = role.substring(5).toLowerCase(Locale.ROOT);
            return "redirect:/" + role;
        }
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/403";
    }

    public void foo() {
        // this method is intentionally left blank
    }
}
