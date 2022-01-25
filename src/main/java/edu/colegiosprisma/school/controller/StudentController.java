package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @Autowired
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
    public String agregar(Model model) {
        Parent parent = getCurrentParent();
        cargarOptions(model);

        model.addAttribute("nombresCompletos", parent.getGivenNames());
        model.addAttribute("student", new Student());
        model.addAttribute("enrollment", new Enrollment());

        return "parent/postulante";
    }

    @PostMapping("/parent/postulante")
    public String registrar(@Valid Student student, BindingResult result, Enrollment enrollment, Model model) {
        Parent parent = getCurrentParent();
        student.setParent(parent);

        if (result.hasErrors()) {
            cargarOptions(model);
            lanzarMensajesAdvertencia(student, model);
            if (student.getAge() > 18) {
                model.addAttribute("alertaEdad", "Debe ser menor a 18 años");
            }
            if (student.getDocumentNumber().length() != student.getDocumentType().getLength()) {
                model.addAttribute("alertaDocumento", "Revise bien su número de documento");
            }
            return "parent/postulante";
        }
        if (!studentService.verifyDuplicate(student)) {
            if (student.getAge() > 18) {
                model.addAttribute("alertaEdad", "Debe ser menor a 18 años");
                return "parent/postulante";
            }
            studentService.create(student, enrollment); // Inserta en la base de datos
            return "redirect:/parent/admision";
        } else {
//            cargarOptions(model);
//            lanzarMensajesAdvertencia(parent, model);
          /*  if (parent.getAge() < 18 && !studentService.verifyDuplicate(student)) {
                cargarOptions(model);
                lanzarMensajesAdvertencia(student, model);
                model.addAttribute("alertaEdad", "Debe ser menor a 18 años");
            } else if (!studentService.verifyDuplicate(student)) {
                cargarOptions(model);
                lanzarMensajesAdvertencia(student, model);
            } else {
                model.addAttribute("alertaEdad", "Debe ser menor a 18 años");
            }*/

            return "parent//postulante";
        }
    }

    @GetMapping(value = "/parent/postulante/grados")
    public @ResponseBody
    List<Grade> getGradosPorNivel(@RequestParam(value = "levelId") Integer levelId) {
        Optional<Level> level = levelService.findLevelById(levelId);
        return level.isPresent() ? gradeService.getAllGradesByLevel(level.get()) : new ArrayList<>();
    }

    private void cargarOptions(Model model) {
        List<DocumentType> documentTypeList = documentTypeService.getAll();
        List<Gender> genderList = genderService.getAll();
        List<Nationality> nationalityList = nationalityService.getAll();
        List<Relationship> relationshipList = relationshipService.getAll();
        List<Level> levelList = levelService.getAll();

        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
        model.addAttribute("relationshipList", relationshipList);
        model.addAttribute("levelList", levelList);
    }

    private void lanzarMensajesAdvertencia(@Valid Student student, Model model) {

        if (studentService.verifyDuplicate(student))
            model.addAttribute("alertaDocumentNumber", "El " + student.getDocumentType().getName() + " ingresado ya existe");

    }

    private Parent getCurrentParent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return parentService.findByUsername(userDetails.getUsername());
    }
}
