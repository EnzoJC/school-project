package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

@Controller
public class UserController {

    @Autowired
    private IParentService parentService;

    @GetMapping({"/", "/login"})
    public String index() {
        return "index";
    }

//    @GetMapping({"/parent", "/parent/admision"})
//    public String parent(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        Parent parent = parentService.selectByUsername(userDetails.getUsername());
//
//        model.addAttribute("nombresCompletos", parent.getGivenNames());
//        return "admision";
//    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
