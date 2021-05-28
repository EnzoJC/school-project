package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
    @Autowired
    private IParentService parentService;

    @GetMapping("/registro")
    public String registration(Model model) {
        model.addAttribute("parent", new Parent());
        return "registro";
    }

    @PostMapping("/registro")
    public String registration(@ModelAttribute("parent") Parent parent, BindingResult bindingResult) {
        parentService.guardar(parent);
        return "redirect:/registro";
    }

    @GetMapping({"/", "/login"})
    public String index() {
        return "index";
    }

//    @GetMapping("/menu")
//    public String menu() {
//        return "menu";
//    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/";
        }
        return "redirect:/user/";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
