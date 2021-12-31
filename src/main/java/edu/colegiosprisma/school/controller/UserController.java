package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.PaymentType;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IPaymentRepository;
import edu.colegiosprisma.school.repository.IPaymentTypeRepository;
import edu.colegiosprisma.school.service.IParentService;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/login"})
    public String index() {
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
}
