package edu.colegiosprisma.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

@Controller
public class UserController {

    @GetMapping({"/", "/login"})
    public String index() {
        return "index";
    }

    @GetMapping({"/parent", "/parent/admision"})
    public String parent() {
        return "admision";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
