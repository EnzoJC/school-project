package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;

import edu.colegiosprisma.school.service.IParentService;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParentController {
    @Qualifier("IParentServImpl")
    @Autowired
    private IParentService parentService;

    @GetMapping("/registro")
    public String registrar(Model model) {
        Parent parent = new Parent();
        model.addAttribute("parent", parent);
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(Parent parent) {
        parentService.create(parent);
        return "redirect:/registrar";
    }

}
