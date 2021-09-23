package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.dto.EmailBody;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.entity.Gender;
import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.repository.INationalityRepository;
import edu.colegiosprisma.school.service.IDocumentTypeService;
import edu.colegiosprisma.school.service.IGenderService;
import edu.colegiosprisma.school.service.INationalityService;
import edu.colegiosprisma.school.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ParentController {

//    private final IParentService parentService;
//    @Autowired
//    public ParentController(IParentService iParentService){
//        this.parentService = iParentService;
//    }

    @Autowired
    private IParentService parentService;

    @Autowired
    private EmailController emailController;

    @Autowired
    private IDocumentTypeService documentTypeService;

    @Autowired
    private INationalityService nationalityService;

    @Autowired
    private IGenderService genderService;

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
    public String registrar(Parent parent) {
        parentService.create(parent); // Inserta en la base de datos
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail(parent.getEmail());
        emailBody.setSubject("Registro de Matrícula - Colegios Prisma");
        emailBody.setContent(
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Document</title>\n" +
                "<!-- Inicio de Codigo CSS -->\n" +
                "<style>\n" +
                "html {\n" +
                "margin: 0px;\n" +
                "height: 100%;\n" +
                "width: 100%;\n" +
                "}\n" +
                "body {\n" +
                "margin: 0px;\n" +
                "min-height: 100%;\n" +
                "width: 100%;\n" +
                "}\n" +
                "body {\n" +
                "font-family: verdana;\n" +
                "text-align: center;\n" +
                "hyphens: auto;\n" +
                "background-color: #D3D3D3;\n" +
                "}\n" +
                ".titulo {\n" +
                "text-align: center;\n" +
                "color: #3278B6;\n" +
                "}\n" +
                ".negrita {\n" +
                "font-weight: bold;\n" +
                "}\n" +
                ".contenido{\n" +
                "position: absolute;\n" +
                "top: 50%; left: 50%;\n" +
                "transform: translate(-50%,-50%);\n" +
                "width: 30rem;\n" +
                "height: 30rem;\n" +
                "}\n" +
                ".cuadrado {\n" +
                "width: 30rem;\n" +
                "height: 30rem;\n" +
                "padding: 5rem 5rem;\n" +
                "box-shadow: 0px 0px 10px 5px #2d2d2d14;\n" +
                "background-color: white;\n" +
                "position: absolute;\n" +
                "top: 50%;\n" +
                "left: 50%;\n" +
                "-ms-transform: translate(-50%, -50%);\n" +
                "transform: translate(-50%, -50%);\n" +
                "}\n" +
                "hr {\n" +
                "width: 15%;\n" +
                "margin-top: 35px;\n" +
                "margin-bottom: 35px;\n" +
                "}\n" +
                ".etiqueta {\n" +
                "font-size: 10px;\n" +
                "}\n" +
                ".credencial {\n" +
                "font-family: \"Courier New\", \"Lucida Console\", monospace;\n" +
                "}\n" +
                ".boton a{\n" +
                "-webkit-text-size-adjust: none;\n" +
                "text-decoration: none;\n" +
                "display: inline-block;\n" +
                "color: #ffffff;\n" +
                "background-color: #3278B6;\n" +
                "width: auto;\n" +
                "border: 1px solid #3278B6;\n" +
                "padding-top: 15px;\n" +
                "padding-bottom: 15px;\n" +
                "text-align: center;\n" +
                "word-break: keep-all;\n" +
                "padding-left: 50px;\n" +
                "padding-right: 50px;\n" +
                "}\n" +
                ".texto{\n" +
                "text-align: justify;\n" +
                "}\n" +
                "@media (max-width: 480px) {\n" +
                ".cuadrado {\n" +
                "position: absolute;\n" +
                "width: 15rem;\n" +
                "height: 40rem;\n" +
                "top: 50%;\n" +
                "left: 50%;\n" +
                "-ms-transform: translate(-50%, -50%);\n" +
                "transform: translate(-50%, -50%);\n" +
                "padding: 4rem;\n" +
                "box-shadow: 0px 0px 10px 5px #2d2d2d14;\n" +
                "background-color: white;\n" +
                "}\n" +
                ".contenido{\n" +
                "position: absolute;\n" +
                "width: 20rem;\n" +
                "height: 30rem;\n" +
                "top: 50%; \n" +
                "left: 50%;\n" +
                "transform: translate(-50%,-50%);\n" +
                "-ms-transform: translate(-50%, -50%);\n" +
                "/* padding: 1rem 1rem; */\n" +
                "}\n" +
                "}\n" +
                "</style>\n" +
                "<!-- Fin de Codigo CSS -->\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"cuadrado\">\n" +
                "<div class=\"contenido\">\n" +
                "<div class=\"titulo\"><h1> Colegios Prisma</h1></div>\n" +
                "<div class=\"texto\">" +
                "<p>Hola, " + parent.getGivenNames() +
                " queremos informarte que tu cuenta ha sido creada exitosamente.<span class=\"negrita\">\n" +
                "A continuaci&oacute;n veras tus credenciales con las que deber&aacute;s iniciar sesi&oacute;n para\n" +
                "seguir con el proceso\n" +
                "de matr&iacute;cula </span>\n" +
                "</p>\n" +
                "</div>\n" +
                "<hr>\n" +
                "<div class=\"etiqueta\"><h3>Usuario</h3></div>" +
                "<div class=\"credencial\"><h3>" + parent.getUsername() + "</h3></div>\n" +
                "<div class=\"etiqueta\"><h3>Contrase&ntilde;a</h3>\n" +
                "</div><div class=\"credencial\"><h3>" + parent.getDocumentNumber() + "</h3>\n" +
                "</div><div class=\"boton\"><a href=\"#\">Inicia sesi&oacute;n &rarr;</a></div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>"
        );

//        emailBody.setContent("Hola, " + parent.getGivenNames() + ". " +
//                            "Estes es tu usuario: " + parent.getUsername() + ". " +
//                            "Este es tu contraseña: " + parent.getDocumentNumber());
        emailController.enviarEmail(emailBody);
        return "redirect:/index.html";
    }

}
