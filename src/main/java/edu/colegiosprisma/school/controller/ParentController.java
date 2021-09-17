package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.dto.EmailBody;
import edu.colegiosprisma.school.entity.Parent;

import edu.colegiosprisma.school.service.IParentService;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParentController {
    @Qualifier("IParentServImpl")
    @Autowired
    private IParentService parentService;
    @Autowired
    private EmailController emailController;

    @GetMapping("/registro")
    public String agregar(Model model) {
        model.addAttribute("parent", new Parent());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(Parent parent) {
        parentService.create(parent); // Inserta en la base de datos
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail(parent.getEmailAddress());
        emailBody.setSubject("Registro de Matrícula - Colegios Prisma");
        emailBody.setContent("Hola, " + parent.getGivenNames() + " . " +
                            "Estes es tu usuario: " + parent.getUsername() + ". " +
                            "Este es tu contraseña: " + parent.getDocumentNumber());
        emailController.enviarEmail(emailBody);
        return "redirect:/";
    }

}
