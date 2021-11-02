package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private IParentService parentService;

    @GetMapping({"/", "/login"})
    public String index() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/403";
    }
}
