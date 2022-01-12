package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.service.*;
import edu.colegiosprisma.school.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ParentController {
    private final IParentService parentService;
    private final IDocumentTypeService documentTypeService;
    private final INationalityService nationalityService;
    private final IGenderService genderService;
    private final IEmailService emailService;
    private final IStudentService studentService;
    private final IDepartmentService departmentService;
    private final IProvinceService provinceService;
    private final IDistrictService districtService;
    private final ILanguageService languageService;
    private final IReligionService religionService;
    private final ITypeDisabilityService typeDisabilityService;
    private final IBloodTypeService bloodTypeService;
    private final ITypeOfBirthService typeOfBirthService;
    private final IEducationDegreeService educationDegreeService;
    private final IOccupationService occupationService;

    @Autowired
    public ParentController(IParentService parentService, IDocumentTypeService documentTypeService,
                            INationalityService nationalityService, IGenderService genderService,
                            IEmailService emailService, IStudentService studentService, IDepartmentService departmentService,
                            IProvinceService provinceService, IDistrictService districtService, ILanguageService languageService,
                            IReligionService religionService, ITypeDisabilityService typeDisabilityService,
                            IBloodTypeService bloodTypeService, ITypeOfBirthService typeOfBirthService,
                            IEducationDegreeService educationDegreeService, IOccupationService occupationService) {
        this.parentService = parentService;
        this.documentTypeService = documentTypeService;
        this.nationalityService = nationalityService;
        this.genderService = genderService;
        this.emailService = emailService;
        this.studentService = studentService;
        this.departmentService = departmentService;
        this.provinceService = provinceService;
        this.districtService = districtService;
        this.languageService = languageService;
        this.religionService = religionService;
        this.typeDisabilityService = typeDisabilityService;
        this.bloodTypeService = bloodTypeService;
        this.typeOfBirthService = typeOfBirthService;
        this.educationDegreeService = educationDegreeService;
        this.occupationService = occupationService;
    }

    @GetMapping("/registro")
    public String agregar(Model model) {
        List<DocumentType> documentTypeList = documentTypeService.getAll();
        List<Gender> genderList = genderService.getAll();
        List<Nationality> nationalityList = nationalityService.getAll();

        model.addAttribute("parent", new Parent());
        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@Valid Parent parent, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            cargarOptions(model);
            lanzarMensajesAdvertencia(parent, model);
            if (parent.getAge() < 18) {
                model.addAttribute("alertaEdad", "Debe ser mayor a 18 años");
            }
            if (parent.getDocumentNumber().length() != parent.getDocumentType().getLength()) {
                model.addAttribute("alertaDocumento", "Revise bien su número de documento");
            }
            return "registro";
        }
        if (parentService.verifyDuplicate(parent).isEmpty()) {
            if ((parent.getDocumentNumber().length() != parent.getDocumentType().getLength()) && (parent.getAge() < 18)) {
                cargarOptions(model);
                model.addAttribute("alertaEdad", "Debe ser mayor a 18 años");
                model.addAttribute("alertaDocumento", "Revise bien su número de documento");
                return "registro";
            } else if (parent.getDocumentNumber().length() != parent.getDocumentType().getLength()) {
                cargarOptions(model);
                model.addAttribute("alertaDocumento", "Revise bien su número de documento");
                return "registro";
            } else if (parent.getAge() < 18) {
                cargarOptions(model);
                model.addAttribute("alertaEdad", "Debe ser mayor a 18 años");
                return "registro";
            }
            parentService.create(parent);
            emailService.send(parent, "mail/credentials");
            redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso, sus credenciales han sido enviadas al correo registrado");
            return "redirect:/registro";
        } else {
            if (parent.getAge() < 18 && !parentService.verifyDuplicate(parent).isEmpty()) {
                cargarOptions(model);
                lanzarMensajesAdvertencia(parent, model);
                model.addAttribute("alertaEdad", "Debe ser mayor a 18 años");
            } else if (!parentService.verifyDuplicate(parent).isEmpty()) {
                cargarOptions(model);
                lanzarMensajesAdvertencia(parent, model);
            } else {
                cargarOptions(model);
                model.addAttribute("alertaEdad", "Debe ser mayor a 18 años");
            }
            return "registro";
        }
    }

    @GetMapping({"/parent", "/parent/admision"})
    public String admision(Model model) {
        Parent parent = getCurrentParent();
        model.addAttribute("listaEstudiantes", parent.getStudents());
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        return "admision";
    }

    @GetMapping("/parent/perfil")
    public String editarPerfil(Model model) {
        Parent parent = getCurrentParent();
        model.addAttribute("parent", parent);
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        return "perfilParent";
    }

    @PostMapping("/parent/perfil")
    public String actualizarPerfil(@ModelAttribute("parent") Parent parent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String id = userDetails.getUsername();
        parentService.update(parent, id);
        return "redirect:/parent/perfil";
    }

    @GetMapping("/parent/ficha-matricula")
    public String cargarFichaMatricula(@RequestParam(name = "id") String studentId, Model model) {
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
        List<TypeDisability> typeDisabilities = typeDisabilityService.getAll();
        List<TypeOfBirth> typeOfBirths = typeOfBirthService.getAll();
        List<DocumentType> documentTypes = documentTypeService.getAll();


        model.addAttribute("parent", parent);
        if (studentService.findById(studentId).isPresent()) {
            model.addAttribute("student", (Student) studentService.findById(studentId).get());
        }
        model.addAttribute("enrollmentForm", new EnrollmentForm());
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        model.addAttribute("bloodTypes", bloodTypes);
        model.addAttribute("departments", departments);
        model.addAttribute("educationDegrees", educationDegrees);
        model.addAttribute("languages", languages);
        model.addAttribute("occupations", occupations);
        model.addAttribute("religions", religions);
        model.addAttribute("typeDisabilities", typeDisabilities);
        model.addAttribute("typeOfBirths", typeOfBirths);
        model.addAttribute("documentTypes", documentTypes);

        return "fichaMatricula";
    }

    @PostMapping("/parent/ficha-matricula")
    public String guardarFichaMatricula(@ModelAttribute("enrollmentForm") EnrollmentForm enrollmentForm, Model model) {
        return "";
    }

    @GetMapping(value = "/parent/ficha-matricula/provincias")
    public @ResponseBody
    List<Province> getProvinciasPorDepartamento(@RequestParam(value = "id") String id) {
        Department department = departmentService.findById(id);
        return provinceService.getAllProvincesByDepartment(department);
    }

    @GetMapping(value = "/parent/ficha-matricula/distritos")
    public @ResponseBody
    List<District> getDistritosPorProvincia(@RequestParam(value = "id") String id) {
        Province province = provinceService.findById(id);
        return districtService.getAllDistrictsByProvince(province);
    }

    private void cargarOptions(Model model) {
        List<DocumentType> documentTypeList = documentTypeService.getAll();
        List<Gender> genderList = genderService.getAll();
        List<Nationality> nationalityList = nationalityService.getAll();

        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
    }

    private void lanzarMensajesAdvertencia(@Valid Parent parent, Model model) {
        for (int i = 0; i < parentService.verifyDuplicate(parent).size(); i++) {
            if (parentService.verifyDuplicate(parent).get(i) == 1)
                model.addAttribute("alertaDocumentNumber", "El " + parent.getDocumentType().getName() + " ingresado ya existe");
            if (parentService.verifyDuplicate(parent).get(i) == 2)
                model.addAttribute("alertaEmail", "El correo ingresado ya existe");
            if (parentService.verifyDuplicate(parent).get(i) == 3)
                model.addAttribute("alertaPhone", "El teléfono ingresado ya existe");
        }
    }

    private Parent getCurrentParent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return parentService.findByUsername(userDetails.getUsername());
    }
}