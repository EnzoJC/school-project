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

    @Autowired
    public ParentController(IParentService parentService, IDocumentTypeService documentTypeService,
                            INationalityService nationalityService, IGenderService genderService,
                            IEmailService emailService, IStudentService studentService) {
        this.parentService = parentService;
        this.documentTypeService = documentTypeService;
        this.nationalityService = nationalityService;
        this.genderService = genderService;
        this.emailService = emailService;
        this.studentService = studentService;
    }

    /**
     * Cuando se llame a .../registro se abrirá una solicitud tipo GET que llamara al método agregar.
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
            for (int i = 0; i < parentService.verifyParentDuplicate(parent).size(); i++) {
                if (parentService.verifyParentDuplicate(parent).get(i) == 1)
                    model.addAttribute("alertaDocumentNumber", "El " + parent.getDocumentType().getName() + " ingresado ya existe");
                if (parentService.verifyParentDuplicate(parent).get(i) == 2)
                    model.addAttribute("alertaEmail", "El correo ingresado ya existe");
                if (parentService.verifyParentDuplicate(parent).get(i) == 3)
                    model.addAttribute("alertaPhone", "El teléfono ingresado ya existe");
            }
            return "registro";
        }
        if (parentService.verifyParentDuplicate(parent).isEmpty()) {
            parentService.createParent(parent);
            emailService.sendEmail(parent, "mail/credentials");
            redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso, sus credenciales han sido enviadas al correo registrado");
            return "redirect:/registro";
        } else {
            cargarOptions(model);
            for (int i = 0; i < parentService.verifyParentDuplicate(parent).size(); i++) {
                if (parentService.verifyParentDuplicate(parent).get(i) == 1)
                    model.addAttribute("alertaDocumentNumber", "El " + parent.getDocumentType().getName() + " ingresado ya existe");
                if (parentService.verifyParentDuplicate(parent).get(i) == 2)
                    model.addAttribute("alertaEmail", "El correo ingresado ya existe");
                if (parentService.verifyParentDuplicate(parent).get(i) == 3)
                    model.addAttribute("alertaPhone", "El teléfono ingresado ya existe");
            }
            return "registro";
        }
    }

    @GetMapping({"/parent", "/parent/admision"})
    public String admision(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());

        model.addAttribute("listaEstudiantes", parent.getStudents());
        model.addAttribute("nombresCompletos", parent.getGivenNames());
        return "admision";
    }

    @GetMapping("/parent/perfil")
    public String editarPerfil(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent parent = parentService.selectByUsername(userDetails.getUsername());

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
    public String fichaMatricula(@RequestParam(name = "id") String studentId, Model model) {
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

        model.addAttribute("parent", parent);
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("enrollmentForm", new EnrollmentForm());
        model.addAttribute("nombresCompletos", parent.getGivenNames());

        return "fichaMatricula";
    }

    private void cargarOptions(Model model) {
        List<DocumentType> documentTypeList  = documentTypeService.getAllDocumentTypes();
        List<Gender> genderList = genderService.getAllGenders();
        List<Nationality> nationalityList = nationalityService.getAllNationalities();

        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("genderList", genderList);
        model.addAttribute("nationalityList", nationalityList);
    }
}