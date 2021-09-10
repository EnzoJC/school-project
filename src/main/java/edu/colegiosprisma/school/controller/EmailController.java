package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.api.EmailPort;
import edu.colegiosprisma.school.dto.EmailBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailPort emailPort;

    @PostMapping(value = "/enviar-email")
    @ResponseBody
    public boolean enviarEmail(@RequestBody EmailBody emailBody)  {
        return emailPort.sendEmail(emailBody);
    }
}
