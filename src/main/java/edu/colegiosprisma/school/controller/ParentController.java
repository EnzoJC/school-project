package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.dto.EmailBody;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.repository.IParentRepository;
import edu.colegiosprisma.school.service.IDocumentTypeService;
import edu.colegiosprisma.school.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParentController {
    @Qualifier("ParentServImpl")
    @Autowired
    private IParentService parentService;

    @Autowired
    private EmailController emailController;

    @Qualifier("DocumentTypeServImpl")
    @Autowired
    private IDocumentTypeService documentTypeService;

    @GetMapping("/registro")
    public String agregar(Model model) {
        model.addAttribute("parent", new Parent());
        List<DocumentType> listaTipoDocumentos = documentTypeService.getAllDocumenTypes();
//        for (int i = 0; i<listaTipoDocumentos.size(); i++){
//            System.out.println(listaTipoDocumentos.get(i).getName());
//            System.out.println(listaTipoDocumentos.get(i).getId());
//        }
        model.addAttribute("listDocTypes", listaTipoDocumentos);
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(Parent parent) {
        parentService.create(parent); // Inserta en la base de datos
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail(parent.getEmail());
        emailBody.setSubject("Registro de Matrícula - Colegios Prisma");
        emailBody.setContent("Hola, " + parent.getGivenNames() + " . " +
                            "Estes es tu usuario: " + parent.getUsername() + ". " +
                            "Este es tu contraseña: " + parent.getDocumentNumber());
        emailController.enviarEmail(emailBody);
        return "redirect:/index.html";
    }

}
