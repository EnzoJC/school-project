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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class ParentController {
    @Autowired
    private IEnrollmentService enrollmentService;
    @Autowired
    private IParentService parentService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IParentInformationService parentInformationService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private IDocumentTypeService documentTypeService;
    @Autowired
    private INationalityService nationalityService;
    @Autowired
    private IGenderService genderService;
    @Autowired
    private ILanguageService languageService;
    @Autowired
    private IReligionService religionService;
    @Autowired
    private ITypeDisabilityService typeDisabilityService;
    @Autowired
    private IBloodTypeService bloodTypeService;
    @Autowired
    private ITypeBirthService typeBirthService;
    @Autowired
    private IEducationDegreeService educationDegreeService;
    @Autowired
    private IOccupationService occupationService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IEnrollmentFormService enrollmentFormService;


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("parent", new Parent());
        loadOptionsRegisterForm(model);
        return "parent/parent-register-form";
    }

    @PostMapping("/register")
    public String register(@Valid Parent parent, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors() || parentService.isDuplicate(parent, model)) {
            loadOptionsRegisterForm(model);
            return "parent/parent-register-form";
        } else {
            parentService.create(parent);
            emailService.send(parent, "mail/credentials");
            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Registro exitoso, sus credenciales han sido enviadas al correo registrado");
            return "redirect:/register";
        }
    }

    @GetMapping({"/parent", "/parent/applicants-list"})
    public String showStudents(Model model) {
        Parent parent = getCurrentParent();
        model.addAttribute("listaEstudiantes", parent.getStudents())
                .addAttribute("nombresCompletos", parent.getGivenNames())
                .addAttribute("description", "");
        return "student/applicants-list";
    }

    @GetMapping("/parent/parent-profile")
    public String updateProfile(Model model) {
        Parent parent = getCurrentParent();
        model.addAttribute("parent", parent)
                .addAttribute("nombresCompletos", parent.getGivenNames());
        return "parent/parent-profile";
    }

    @PostMapping("/parent/parent-profile")
    public String updateProfile(@ModelAttribute("parent") Parent parent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String id = userDetails.getUsername();
        parentService.update(parent, id);
        return "redirect:/parent/parent-profile";
    }

    @GetMapping("/parent/enrollment-form")
    public String loadEnrollmentForm(@RequestParam(name = "id") String studentId, Model model) {
        model.addAttribute("parent", getCurrentParent());
        if (studentService.findById(studentId).isPresent()) {
            Student student = (Student) studentService.findById(studentId).get();
            model.addAttribute("student", student);
        }
        loadOptionsEnrollmentForm(model);
        ParentInformationDto parentInformationDto = new ParentInformationDto();
        parentInformationDto.addParentInformation(new ParentInformation());
        parentInformationDto.addParentInformation(new ParentInformation());

        model.addAttribute("enrollmentForm", new EnrollmentForm())
                .addAttribute("parentInformationDtoList", parentInformationDto)
                .addAttribute("nombresCompletos", getCurrentParent().getGivenNames())
                .addAttribute("typeDisability", new TypeDisability());

        return "student/enrollment-form";
    }

    @PostMapping("/parent/enrollment-form")
    public String saveEnrollmentForm(EnrollmentForm enrollmentForm,
                                     Student student,
                                     ParentInformationDto parentInformationDto,
                                     TypeDisability typeDisability,
                                     Model model) {
        loadOptionsEnrollmentForm(model);
        Student student1 = student;
        System.out.println(student1);
//        enrollmentService.updateStatusForNewStudent(student, 2);

        return "redirect:/parent/applicants-list";
    }

    private Parent getCurrentParent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return parentService.findByUsername(userDetails.getUsername());
    }

    private void loadOptionsRegisterForm(Model model) {
        model.addAttribute("documentTypeList", documentTypeService.getAll())
                .addAttribute("genderList", genderService.getAll())
                .addAttribute("nationalityList", nationalityService.getAll());
    }

    private void loadOptionsEnrollmentForm(Model model) {
        loadOptionsRegisterForm(model);
        model.addAttribute("languageList", languageService.getAll())
                .addAttribute("religionList", religionService.getAll())
                .addAttribute("typeDisabilityList", typeDisabilityService.getAll())
                .addAttribute("bloodTypeList", bloodTypeService.getAll())
                .addAttribute("typeBirthList", typeBirthService.getAll())
                .addAttribute("educationDegreeList", educationDegreeService.getAll())
                .addAttribute("occupationList", occupationService.getAll())
                .addAttribute("departmentList", departmentService.getAll());
    }
}