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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class ParentController {
    private final IEnrollmentService enrollmentService;
    private final IParentService parentService;
    private final IDocumentTypeService documentTypeService;
    private final INationalityService nationalityService;
    private final IGenderService genderService;
    private final IEmailService emailService;
    private final IStudentService studentService;
    private final IDepartmentService departmentService;
    private final ILanguageService languageService;
    private final IReligionService religionService;
    private final ITypeDisabilityService typeDisabilityService;
    private final IBloodTypeService bloodTypeService;
    private final ITypeBirthService typeBirthService;
    private final IEducationDegreeService educationDegreeService;
    private final IOccupationService occupationService;
    private final IParentInformationService parentInformationService;
    private final IEnrollmentFormService enrollmentFormService;

    @Autowired
    public ParentController(IEnrollmentService enrollmentService, IParentService parentService, IDocumentTypeService documentTypeService,
                            INationalityService nationalityService, IGenderService genderService,
                            IEmailService emailService, IStudentService studentService, IDepartmentService departmentService, ILanguageService languageService,
                            IReligionService religionService, ITypeDisabilityService typeDisabilityService,
                            IBloodTypeService bloodTypeService, ITypeBirthService typeBirthService,
                            IEducationDegreeService educationDegreeService, IOccupationService occupationService, IParentInformationService parentInformationService, IEnrollmentFormService enrollmentFormService) {
        this.enrollmentService = enrollmentService;
        this.parentService = parentService;
        this.documentTypeService = documentTypeService;
        this.nationalityService = nationalityService;
        this.genderService = genderService;
        this.emailService = emailService;
        this.studentService = studentService;
        this.departmentService = departmentService;
        this.languageService = languageService;
        this.religionService = religionService;
        this.typeDisabilityService = typeDisabilityService;
        this.bloodTypeService = bloodTypeService;
        this.typeBirthService = typeBirthService;
        this.educationDegreeService = educationDegreeService;
        this.occupationService = occupationService;
        this.parentInformationService = parentInformationService;
        this.enrollmentFormService = enrollmentFormService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<DocumentType> documentTypeList = documentTypeService.getAll();
        List<Gender> genderList = genderService.getAll();
        List<Nationality> nationalityList = nationalityService.getAll();

        model.addAttribute("parent", new Parent());
        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
        return "parent/parent-register-form";
    }

    @PostMapping("/register")
    public String register(@Valid Parent parent, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors() || parentService.isDuplicate(parent, model)) {
            loadOptions(model);
            return "parent/parent-register-form";
        } else {
            parentService.create(parent);
            emailService.send(parent, "mail/credentials");
            // redirectAttributes.addFlashAttribute() este metodo sirve para agregar un mensaje de exito al momento de redireccionar
            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Registro exitoso, sus credenciales han sido enviadas al correo registrado");
            return "redirect:/register";
        }
    }

    @GetMapping({"/parent", "/parent/applicants-list"})
    public String showStudents(Model model) {
        Parent parent = getCurrentParent();
        model.addAttribute("listaEstudiantes", parent.getStudents());
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        model.addAttribute("description","");
        return "student/applicants-list";
    }

    @GetMapping("/parent/parent-profile")
    public String updateProfile(Model model) {
        Parent parent = getCurrentParent();
        model.addAttribute("parent", parent);
        model.addAttribute("nombresCompletos", parent.getGivenNames());
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.findByUsername(userDetails.getUsername());
//        Student student = (Student) studentService.findById(studentId).get();
        List<BloodType> bloodTypes = bloodTypeService.getAll();
        List<Department> departments = departmentService.getAll();
        List<EducationDegree> educationDegrees = educationDegreeService.getAll();
        List<Language> languages = languageService.getAll();
        List<Occupation> occupations = occupationService.getAll();
        List<Religion> religions = religionService.getAll();
        Set<TypeDisability> typeDisabilities = typeDisabilityService.getAll();
        Set<TypeBirth> typeBirths = typeBirthService.getAll();
        List<DocumentType> documentTypes = documentTypeService.getAll();


        model.addAttribute("parent", parent);
        if (studentService.findById(studentId).isPresent()) {
            model.addAttribute("student", (Student) studentService.findById(studentId).get());
        }
        model.addAttribute("enrollmentForm", new EnrollmentForm());
        model.addAttribute("fatherInformation",new ParentInformation());
        model.addAttribute("motherInformation",new ParentInformation());
        model.addAttribute("typeDisability",new TypeDisability());
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        model.addAttribute("bloodTypes", bloodTypes);
        model.addAttribute("departments", departments);
        model.addAttribute("educationDegrees", educationDegrees);
        model.addAttribute("languages", languages);
        model.addAttribute("occupations", occupations);
        model.addAttribute("religions", religions);
        model.addAttribute("typeDisabilities", typeDisabilities);
        model.addAttribute("typeBirths", typeBirths);
        model.addAttribute("documentTypes", documentTypes);

        return "student/enrollment-form";
    }

    @PostMapping("/parent/enrollment-form")
    public String saveEnrollmentForm(@ModelAttribute("enrollmentForm") EnrollmentForm enrollmentForm,@ModelAttribute("student") Student student,
            @ModelAttribute("fatherInformation") ParentInformation fatherInformation,
            @ModelAttribute("motherInformation") ParentInformation motherInformation,
                                     @ModelAttribute("typeDisability") TypeDisability typeDisability,
            Model model) {
        System.out.println("paso");
        // enrollmentFormService.create(enrollmentForm); // pesistir parentinformations y luego persistir enrollmentForm bye bye
        parentInformationService.create(fatherInformation);
        parentInformationService.create(motherInformation);
        enrollmentForm.getParents().add(fatherInformation);
        enrollmentForm.getParents().add(motherInformation);
        enrollmentFormService.create(enrollmentForm,student);
        //Finalizamos cambiando el estado de enrollmen

        enrollmentService.updateStatusForNewStudent(student, 2);

        return "redirect:/parent/applicants-list";
    }

    private void loadOptions(Model model) {
        List<DocumentType> documentTypeList = documentTypeService.getAll();
        List<Gender> genderList = genderService.getAll();
        List<Nationality> nationalityList = nationalityService.getAll();

        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
    }

    private Parent getCurrentParent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return parentService.findByUsername(userDetails.getUsername());
    }
}