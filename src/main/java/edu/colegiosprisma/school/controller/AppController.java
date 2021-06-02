/*package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.service.implementation.IParentServImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    private final IParentServImpl parentServ = new IParentServImpl();

    @GetMapping("/registro")
    public String registration(Model model) {
        model.addAttribute("parent", new Parent());
        return "registro";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") Parent parent) {
        //parentServ.guardar(parent);
        return "redirect:/bienvenido";
    }

    @GetMapping({"/", "/login"})
    public String index() {
        return "index";
    }

    @GetMapping("/parent")
    public String parent() {
        return "parent";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}*/
