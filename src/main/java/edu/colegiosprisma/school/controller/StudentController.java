package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

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
    private ILevelService levelService;

    @Autowired
    private IRelationshipService relationshipService;

    @Autowired
    private IGradeService gradeService;

    @GetMapping("/parent/postulante")
    public String agregar(Model model){
        List<DocumentType> documentTypeList  = documentTypeService.getAllDocumentTypes();
        List<Gender> genderList = genderService.getAllGenders();
        List<Nationality> nationalityList = nationalityService.getAllNationalities();
        List<Relationship> relationshipList = relationshipService.getAllRelationships();
        List<Level> levelList = levelService.getAllLevels();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", parent.getGivenNames());
        model.addAttribute("student", new Student());
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
        model.addAttribute("relationshipList", relationshipList);
        model.addAttribute("levelList", levelList);
        return "postulante";
    }

    @PostMapping("/parent/postulante")
    public String registrar(Student student, Enrollment enrollment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());
        student.setParent(parent);
        studentService.create(student, enrollment); // Inserta en la base de datos
        return "redirect:/parent";
    }
    
    /**
     * Permite obtener todos los grados de un nivel.
     * Este metodo ayuda en el llendado de los drop-down de la vista Postulante (Nivel y grado)
     */
    @GetMapping(value = "/parent/postulante/grados")
    public @ResponseBody List<Grade> getGradosPorNivel(@RequestParam(value = "levelId", required = true) Integer levelId) {
        Optional<Level> level = levelService.getLevel(levelId);
        return gradeService.getAllGradesByLevel(level.get());
    }
}
