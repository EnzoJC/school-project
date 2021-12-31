package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {
    private final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final TemplateEngine templateEngine; // Permite renderizar un template
    private final JavaMailSender javaMailSender; // Permite enviar un email

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    // sendEmailTool: esta función es para enviar correos
    @Override
    public String sendEmail(Parent parent, String templateEngine) {
        Context context = new Context(); // contexto para el template // El contexto es un objeto que permite pasar datos a un template
        context.setVariable("parent", parent); // variable para el template
        // templateEngine.process: procesa el template
        String process = this.templateEngine.process(templateEngine, context); // proceso del template
        // MimeMessage: es una clase que permite enviar un email
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // mensaje de correo
        // MimeMessageHelper: ayuda a crear el mensaje de correo y enviarlo
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); // helper para el mensaje de correo
        try {
            helper.setSubject("Registro de Matrícula - Colegios Prisma"); // asunto del mensaje
            helper.setText(process, true); // texto del mensaje. true: para que el mensaje sea html
            helper.setTo(parent.getEmail()); // destinatario del mensaje
            javaMailSender.send(mimeMessage); // enviar el mensaje
        } catch (MessagingException e) {
            LOGGER.info("Email", e.toString()); // imprimir el error
        }
        return "enviado";
    }
}