package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Login;
import edu.colegiosprisma.school.entity.Tutor;
import edu.colegiosprisma.school.repository.ITutorRepository;
import edu.colegiosprisma.school.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class StartController {
    @Autowired
    private ITutorRepository iTutorRepository;
    private ILoginService iLoginService;

    @GetMapping("/registro")
    public String FormularioRegistro(Model model){
        model.addAttribute("login", new Login());
        return "registro";
    }

//    public String registrarTutor(@Valid @ModelAttribute Tutor, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "redirect:/auth/registro";
//        }
//        else {
//            model.addAttribute("tutor")
//        }
//
//    }
}

