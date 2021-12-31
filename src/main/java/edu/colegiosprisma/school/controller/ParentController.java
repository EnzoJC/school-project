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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

// @Controller es una anotación que indica que la clase es un controlador
@Controller
// ParentController permite gestionar los datos de los padres
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

    /**
     * Cuando se llame a /registro se abrirá una solicitud tipo GET que llamara al método agregar.
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
    public String registrar(@Valid Parent parent, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if(result.hasErrors()){
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
        if (parentService.verifyParentDuplicate(parent).isEmpty()) {
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
            parentService.createParent(parent);
            emailService.sendEmail(parent, "mail/credentials");
            redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso, sus credenciales han sido enviadas al correo registrado");
            return "redirect:/registro";
        } else {
            if (parent.getAge() < 18 && !parentService.verifyParentDuplicate(parent).isEmpty()) {
                cargarOptions(model);
                lanzarMensajesAdvertencia(parent, model);
                model.addAttribute("alertaEdad", "Debe ser mayor a 18 años");
            } else if(!parentService.verifyParentDuplicate(parent).isEmpty()){
                cargarOptions(model);
                lanzarMensajesAdvertencia(parent, model);
            } else{
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

    // @GetMapping: es para indicar que es una petición GET
    // Una petición GET es una petición que se hace para obtener datos
    @GetMapping("/parent/ficha-matricula")
    public String cargarFichaMatricula(@RequestParam(name = "id") String studentId, Model model) {
        // SecurityContextHolder.getContext() permite obtener el contexto de seguridad
        // getAuthentication() obtiene la autenticación del usuario
        // Una autenticación del usuario es un objeto que contiene información sobre el usuario que ha iniciado sesión
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // auth.getPrincipal() permitirá obtener el usuario autenticado como un objeto UserDetails
        // UserDetails es una clase que contiene información sobre el usuario que ha iniciado sesión
        // Por ejemplo: getUsername() obtiene el nombre de usuario del usuario autenticado,
        // getAuthorities() obtiene una lista de objetos GrantedAuthority
        // (La clase GrantedAuthority es una clase que contiene información sobre los roles del usuario),
        // que contiene información sobre los roles del usuario,
        // y getPassword() obtiene la contraseña del usuario, etc.
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());
//        Student student = (Student) studentService.getStudentById(studentId).get();
        List<BloodType> bloodTypes = bloodTypeService.getAllBloodTypes();
        List<Department> departments = departmentService.getAllDepartments();
        List<EducationDegree> educationDegrees = educationDegreeService.getAllEducationDegrees();
        List<Language> languages = languageService.getAllLanguages();
        List<Occupation> occupations = occupationService.getAllOccupations();
        List<Religion> religions = religionService.getAllReligions();
        List<TypeDisability> typeDisabilities = typeDisabilityService.getAllTypeOfDisabilities();
        List<TypeOfBirth> typeOfBirths = typeOfBirthService.getAllTypeOfBirths();
        List<DocumentType> documentTypes = documentTypeService.getAllDocumentTypes();


        model.addAttribute("parent", parent);
        if (studentService.getStudentById(studentId).isPresent()) {
            model.addAttribute("student", (Student) studentService.getStudentById(studentId).get());
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
    public @ResponseBody List<Province> getProvinciasPorDepartamento(@RequestParam(value = "id") String id) {
        Department department = departmentService.getDepartment(id);
        return provinceService.getAllProvincesByDepartament(department);
    }

    @GetMapping(value = "/parent/ficha-matricula/distritos")
    public @ResponseBody List<District> getDistritosPorProvincia(@RequestParam(value = "id") String id) {
        Province province = provinceService.getProvince(id);
        return districtService.getAllDistrictsByProvince(province);
    }

    private void cargarOptions(Model model) {
        List<DocumentType> documentTypeList  = documentTypeService.getAllDocumentTypes();
        List<Gender> genderList = genderService.getAllGenders();
        List<Nationality> nationalityList = nationalityService.getAllNationalities();

        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
    }

    private void lanzarMensajesAdvertencia(@Valid Parent parent, Model model) {
        for (int i = 0; i < parentService.verifyParentDuplicate(parent).size(); i++) {
            if (parentService.verifyParentDuplicate(parent).get(i) == 1)
                model.addAttribute("alertaDocumentNumber", "El " + parent.getDocumentType().getName() + " ingresado ya existe");
            if (parentService.verifyParentDuplicate(parent).get(i) == 2)
                model.addAttribute("alertaEmail", "El correo ingresado ya existe");
            if (parentService.verifyParentDuplicate(parent).get(i) == 3)
                model.addAttribute("alertaPhone", "El teléfono ingresado ya existe");
        }
    }

    private Parent getCurrentParent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return parentService.selectByUsername(userDetails.getUsername());
    }
}