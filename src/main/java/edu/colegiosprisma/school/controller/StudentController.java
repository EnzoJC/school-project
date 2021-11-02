package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private final IStudentService studentService;
    private final IParentService parentService;
    private final IDocumentTypeService documentTypeService;
    private final INationalityService nationalityService;
    private final IGenderService genderService;
    private final ILevelService levelService;
    private final IRelationshipService relationshipService;
    private final IGradeService gradeService;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate dateAgo = LocalDate.now().minusYears(18);

    public StudentController(IStudentService studentService, IParentService parentService,
                             IDocumentTypeService documentTypeService, INationalityService nationalityService,
                             IGenderService genderService, ILevelService levelService, IRelationshipService relationshipService,
                             IGradeService gradeService) {
        this.studentService = studentService;
        this.parentService = parentService;
        this.documentTypeService = documentTypeService;
        this.nationalityService = nationalityService;
        this.genderService = genderService;
        this.levelService = levelService;
        this.relationshipService = relationshipService;
        this.gradeService = gradeService;
    }

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

        model.addAttribute("dateAgo", dateAgo);
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
     * Este método ayuda en el llenado de los drop-down de la vista Postulante (Nivel y grado)
     */
    @GetMapping(value = "/parent/postulante/grados")
    public @ResponseBody List<Grade> getGradosPorNivel(@RequestParam(value = "levelId") Integer levelId) {
        Optional<Level> level = levelService.getLevel(levelId);
        return gradeService.getAllGradesByLevel(level.get());
    }
}
