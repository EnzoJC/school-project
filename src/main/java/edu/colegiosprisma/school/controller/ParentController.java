package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.entity.Gender;
import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.service.IDocumentTypeService;
import edu.colegiosprisma.school.service.IGenderService;
import edu.colegiosprisma.school.service.INationalityService;
import edu.colegiosprisma.school.service.IParentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ParentController {

    private final IParentService parentService;
    private final EmailController emailController;
    private final IDocumentTypeService documentTypeService;
    private final INationalityService nationalityService;
    private final IGenderService genderService;

    public ParentController(IParentService parentService, EmailController emailController,
                            IDocumentTypeService documentTypeService, INationalityService nationalityService,
                            IGenderService genderService) {
        this.parentService = parentService;
        this.emailController = emailController;
        this.documentTypeService = documentTypeService;
        this.nationalityService = nationalityService;
        this.genderService = genderService;
    }

    /**
     * Cuando se llame a .../registro se abrirá una solicitud tipo GET que llamara al método agregar.
     * Este método cargara los combos (desde la base de datos) y preparara un objeto de tipo Parent,
     * por último devuelve la vista registro.html
     */
    @GetMapping("/registro")
    public String agregar(Model model) {
        List<DocumentType> documentTypeList  = documentTypeService.getAllDocumentTypes();
        List<Gender> genderList = genderService.getAllGenders();
        List<Nationality> nationalityList = nationalityService.getAllNationalities();

        model.addAttribute("parent", new Parent());
        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(Parent parent, RedirectAttributes redirectAttributes) {
        Parent p = parentService.create(parent); // Inserta en la base de datos
        if (p == null){
            System.out.println("AA:");
            redirectAttributes.addFlashAttribute("alerta", "Usuario ya existe");
            return "redirect:/registro";
        }
//        EmailBody emailBody = new EmailBody();
//        emailBody.setTo(parent.getEmail());
//        emailBody.setSubject("Registro de Matrícula - Colegios Prisma");
//        emailBody.setContent("Hola, " + parent.getGivenNames() + ". " +
//                            "Este es tu usuario: " + parent.getUsername() + ". " +
//                            "Este es tu contraseña: " + parent.getDocumentNumber());
//        emailController.enviarEmail(emailBody);
        return "redirect:/login.html";
    }

    @GetMapping({"/parent", "/parent/admision"})
    public String admision(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());

        model.addAttribute("listaEstudiantes", parent.getStudents());
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        return "admision";
    }

    @GetMapping("/parent/perfil")
    public String editarPerfil(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());

        model.addAttribute("parent", parent);
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        return "perfilParent";
    }

    @PostMapping("/parent/perfil")
    public String actualizarPerfil(@ModelAttribute("parent") Parent parent) {
        System.out.println("Usuario: " + parent.getFirstLastName());
//        parentService.update(parent);
        return "redirect:/perfil";
    }
}