package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParentController {

    @GetMapping("/registro")
    public String registration(Model model) {
//        if (securityService.isAuthenticated()) {
//            return "redirect:/";
//        }
        model.addAttribute("parent", new Parent());
        return "registro";
    }

}
