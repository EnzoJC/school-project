package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IDocumentTypeService documentTypeService;
    @Autowired
    private IGenderService genderService;
    @Autowired
    private INationalityService nationalityService;
    @Autowired
    private IRelationshipService relationshipService;
    @Autowired
    private ILevelService levelService;

    @GetMapping("/parent/applicants-form")
    public String register(Model model) {
        Parent parent = studentService.getParentLogged();
        model.addAttribute("nombresCompletos", parent.getGivenNames())
                .addAttribute("student", new Student())
                .addAttribute("enrollment", new Enrollment());
        loadOptions(model);

        return "student/applicant-form";
    }

    @PostMapping("/parent/applicants-form")
    public String register(@Valid Student student, BindingResult result, Enrollment enrollment, Model model) {
        if (result.hasErrors() || studentService.isDuplicate(student, model)) {
            loadOptions(model);
            return "student/applicant-form";
        } else {
            studentService.create(student, enrollment); // Inserta en la base de datos
            return "redirect:/parent/applicants-list";
        }
    }

    private void loadOptions(Model model) {
        model.addAttribute("documentTypeList", documentTypeService.getAll())
                .addAttribute("genderList", genderService.getAll())
                .addAttribute("nationalityList", nationalityService.getAll())
                .addAttribute("relationshipList", relationshipService.getAll())
                .addAttribute("levelList", levelService.getAll());
    }
}
