package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;
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
    private static final String PREFIX_ROLE = "ROLE_";
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
            role = role.substring(PREFIX_ROLE.length()).toLowerCase(Locale.ROOT);
            return "redirect:/" + role;
        }
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/admin";
    }

    @GetMapping("/admin/debts")
    public String debts(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/debts";
    }

    @GetMapping("/admin/payments")
    public String payments(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/payments";
    }

    @GetMapping("/admin/parents")
    public String parents(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/parents";
    }

    @GetMapping("/admin/schoolyear")
    public String schoolYear(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/schoolyear";
    }

    @GetMapping("/admin/students")
    public String students(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/students";
    }

    @GetMapping("/admin/transaction")
    public String transaction(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/transaction";
    }

    @GetMapping("/admin/paymentTypes")
    public String paymentTypes(Model model) {
        User user = getCurrentParent();

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/403";
    }

    private User getCurrentParent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userService.findByUsername(userDetails.getUsername());
    }
}