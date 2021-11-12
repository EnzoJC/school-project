package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/email")
public class EmailController {
    @Autowired
    private IEmailService iEmailService;

    @PostMapping(value = "/enviar-credenciales")
    @ResponseBody
    public String enviarEmail(@RequestBody Parent parent)  {
        return iEmailService.sendEmail(parent, "mail/credentials");
    }


}
