package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.service.*;
import org.aspectj.asm.IRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IParentService parentService;

    @Autowired
    private IDocumentTypeService documentTypeService;

    @Autowired
    private INationalityService nationalityService;

    @Autowired
    private IGenderService genderService;

    @Autowired
    private IRelationshipService relationshipService;

    @GetMapping("/parent/postulante")
    public String agregar(Model model){
        List<DocumentType> documentTypeList  = documentTypeService.getAllDocumentTypes();
        List<Gender> genderList = genderService.getAllGenders();
        List<Nationality> nationalityList = nationalityService.getAllNationalities();
        List<Relationship> relationshipList = relationshipService.getAllRelationships();

        model.addAttribute("student", new Student());
        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
        model.addAttribute("relationshipList", relationshipList);
        return "postulante";
    }
    @PostMapping("/parent/postulante")
    public String registrar(Student student) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());
        student.setParent(parent);
        studentService.create(student); // Inserta en la base de datos
        return "redirect:/parent";
    }
}
