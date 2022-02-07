package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {
    private final IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/parent/applicants-form")
    public String register(Model model) {
        Parent parent = studentService.getParentLogged();

        model.addAttribute("nombresCompletos", parent.getGivenNames());
        model.addAttribute("student", new Student());
        model.addAttribute("enrollment", new Enrollment());

        return "student/applicant-form";
    }

    @PostMapping("/parent/applicants-form")
    public String register(@Valid Student student, BindingResult result, Enrollment enrollment, Model model) {
        if (result.hasErrors() || studentService.isDuplicate(student, model)) {
            return "student/applicant-form";
        } else {
            studentService.create(student, enrollment); // Inserta en la base de datos
            return "redirect:/parent/applicants-list";
        }
    }
}
